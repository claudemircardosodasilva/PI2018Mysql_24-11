package com.projeto.pi2018.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projeto.pi2018.azure.Azure;
import com.projeto.pi2018.model.Pessoa;
import com.projeto.pi2018.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {
	
	
	@Autowired
	private PessoaService ps;
	
	@PostMapping("/criar")
	public void criar(Pessoa pessoa) {
		ps.inserir(pessoa);
	}
	@GetMapping("/buscar/{id}")
	public Pessoa buscar(@PathVariable("id") Long id) {
		
		return ps.buscar(id);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable("id") Long id) {
		ps.excluir(id);
	}
	
	@PutMapping("/alterar/{id}")
	public void alterar(@PathVariable("id") Long id) {
		criar(buscar(id));
		
	}
	/*O método detectFace() da classe controller chama os métodos da service para fazer o reconhecimento de face e
	 *  caso seja cadastrado ele apenas usa o addFace() e o treinar
	 *  caso não seja cadastrado ele chama o método criarPerson(), cria a pessoa no banco e utiliza recursão
	*/
	@PostMapping("/detectFace")
	public @ResponseBody boolean detectFace(@RequestBody Pessoa pessoa) throws Exception {
		Azure azure = new Azure();
		String faceId = azure.reconhecerFace(pessoa.getFotoTemp()); 
		if(faceId.length()==36) {
			Pessoa p = ps.buscarCpf(pessoa.getCpf());
			if(p != null) {
				addFace(p.getPersonId(), pessoa.getFotoTemp());
				treinar();
				return true;
			}
			else {
				String personId = criarPerson(pessoa.getNome());
				pessoa.setPersonId(personId);
				criar(pessoa);
				detectFace(pessoa);
			}
		}
		return false;
	}
	public void treinar() throws Exception {
		Azure azure = new Azure();
		azure.train();
	}
	@RequestMapping("/criarPerson")
	public String criarPerson(String nome) throws Exception {
		Azure grupo = new  Azure();
		return grupo.criarPerson(nome);
	}
	@RequestMapping("/addFace")
	public void addFace(String personId,String faceId) throws Exception {
		Azure azure = new  Azure();
		
		azure.addFace(personId,faceId);
	}
	/*O método identify() da classe controller chama os métodos da service para fazer o identificar a face 
	 *  caso aquela pessoa já tenha sido cadastrada
	 *  o retorno tem sucesso se o confidence for >= 0.50 
	*/
	@PostMapping("/identify")
	public @ResponseBody Pessoa identify(@RequestBody String base64) throws Exception {
		Azure azure = new Azure();
		String faceId = azure.reconhecerFace(base64);
		if(faceId!="Erro") {
			String i = azure.identify(faceId);
			String pId = i.substring(77, 113);
			String confidence = i.substring(128,132);
			if(Double.parseDouble(confidence) >= 0.50) {
				Pessoa p = ps.buscarPersonId(pId);
				p.setConfidence(confidence);
				p.setFotoTemp(base64);
				return p;
			}
			else {
				return null;
			}
		}
		
		return null;
	}
}
