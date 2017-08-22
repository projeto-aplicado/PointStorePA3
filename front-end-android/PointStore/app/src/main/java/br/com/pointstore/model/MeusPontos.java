package br.com.pointstore.model;


public class MeusPontos{

	private Integer idMeusPontos;
	private String quantidade;
	private String loja;


	public MeusPontos(String quantidade, String loja){
		this.quantidade = quantidade;
		this.loja = loja;
	}


	public Integer getIdMeusPontos() {
		return idMeusPontos;
	}

	public void setIdMeusPontos(Integer idMeusPontos) {
		this.idMeusPontos = idMeusPontos;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getLoja() {
		return loja;
	}

	public void setLoja(String loja) {
		this.loja = loja;
	}

}
