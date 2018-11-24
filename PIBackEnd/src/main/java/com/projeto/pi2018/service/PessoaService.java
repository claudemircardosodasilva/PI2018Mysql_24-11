package com.projeto.pi2018.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.pi2018.model.Pessoa;
import com.projeto.pi2018.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pr;
	
	public void inserir(Pessoa pessoa) {
		pr.save(pessoa);
	}
	
	public Pessoa buscar(Long id) {
		return pr.getOne(id);
	}
	public void excluir(Long id) {
		pr.deleteById(id);
	}
	
	public Pessoa buscarCpf(String cpf) {
		return pr.buscarCpf(cpf);
		
	}
	public Pessoa buscarPersonId(String personId) {
		return pr.buscarPersonId(personId);
		
	}

}
