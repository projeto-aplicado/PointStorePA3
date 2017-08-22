package br.com.pointstore.model;


public class MinhasQualificacoes{

	private Integer idMinhasQualificacoes;
	private Integer quantidade;
	private Qualificacao qualificacao;

	public Integer getIdMinhasQualificacoes() {
		return idMinhasQualificacoes;
	}

	public void setIdMinhasQualificacoes(Integer idMinhasQualificacoes) {
		this.idMinhasQualificacoes = idMinhasQualificacoes;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Qualificacao getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(Qualificacao qualificacao) {
		this.qualificacao = qualificacao;
	}
	
}
