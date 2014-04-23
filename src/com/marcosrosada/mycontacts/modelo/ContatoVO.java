package com.marcosrosada.mycontacts.modelo;

public class ContatoVO {

	private Long id;
	private String nome;
	private String fone;

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
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public String toString(){
		return id + " - " + nome;
	}
}
