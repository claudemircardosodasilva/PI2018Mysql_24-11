package com.projeto.pi2018.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "pessoa")
public class Pessoa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/*o atibuto fotoTemp tem a anotação @Transient para não ser
	 *  mapeado pelo hibernate e não criar uma coluna no banco
	 *  ele serve para transportar temporariamente o base64 que vem do front 
	 */
	@Transient
	private String fotoTemp;
	private String nome;
	private String rg;
	private String cpf;
	private String telefone;
	private String email;
	private String personId;
	/*o atibuto confidence tem a anotação @Transient para não ser
	 *  mapeado pelo hibernate e não criar uma coluna no banco
	 *  ele serve para capturar temporariamente a % confidence que vem da azure
	 */
	@Transient
	private String confidence;
	@OneToOne(mappedBy="pessoa", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	private Endereco endereco;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getFotoTemp() {
		return fotoTemp;
	}
	public void setFotoTemp(String fotoTemp) {
		this.fotoTemp = fotoTemp;
	}
	public String getConfidence() {
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	
}
