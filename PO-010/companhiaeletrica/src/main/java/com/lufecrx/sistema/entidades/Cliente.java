package com.lufecrx.sistema.entidades;

public class Cliente {

	private String nome;
	private String cpf;
	private Imovel propriedade;

	public Cliente(String nome, String cpf, Imovel propriedade) {
		this.nome = nome;
		this.cpf = cpf;
		this.propriedade = propriedade;
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

	public Imovel getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(Imovel propriedade) {
		this.propriedade = propriedade;
	}
}
