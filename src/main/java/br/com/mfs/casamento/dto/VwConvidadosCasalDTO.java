package br.com.mfs.casamento.dto;

import java.io.Serializable;

public class VwConvidadosCasalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	private String login;
	private String localidade;
	private String padrinho;
	
	
	public VwConvidadosCasalDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getPadrinho() {
		return padrinho;
	}
	public void setPadrinho(String padrinho) {
		this.padrinho = padrinho;
	}
	
	
}
