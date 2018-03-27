package br.com.pointstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Juan on 11/03/2017.
 */

public class Vendas implements Serializable {


	private Integer idVendas;

	private String titulo;


	private String valor;

	private Loja loja;

	private MeusPontos meusPontos;

	private String status;
	
	
	public Integer getIdVendas() {
		return idVendas;
	}
	public void setIdVendas(Integer idVendas) {
		this.idVendas = idVendas;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Loja getLoja() {
		return loja;
	}
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	public MeusPontos getMeusPontos() {
		return meusPontos;
	}
	public void setMeusPontos(MeusPontos meusPontos) {
		this.meusPontos = meusPontos;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
