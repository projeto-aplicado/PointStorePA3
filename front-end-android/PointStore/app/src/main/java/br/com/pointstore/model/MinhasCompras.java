package br.com.pointstore.model;


public class MinhasCompras{

	private Integer idMinhasCompras;
	private Vendas vendas;

	public Integer getIdMinhasCompras() {
		return idMinhasCompras;
	}

	public void setIdMinhasCompras(Integer idMinhasCompras) {
		this.idMinhasCompras = idMinhasCompras;
	}

	public Vendas getVendas() {
		return vendas;
	}

	public void setVendas(Vendas vendas) {
		this.vendas = vendas;
	}
	
}
