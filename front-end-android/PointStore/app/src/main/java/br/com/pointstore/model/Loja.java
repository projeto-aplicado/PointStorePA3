package br.com.pointstore.model;


public class Loja{
	
	private Integer idLoja;
	private String nome;

	public Loja (Integer idLoja, String nome){
		this.idLoja = idLoja;
		this.nome = nome;
	}

	
	public Integer getIdLoja() {
		return idLoja;
	}
	public void setIdLoja(Integer idLoja) {
		this.idLoja = idLoja;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
