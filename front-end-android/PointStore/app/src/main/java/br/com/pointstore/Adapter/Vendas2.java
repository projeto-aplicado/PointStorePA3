package br.com.pointstore.Adapter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


import br.com.pointstore.model.MeusPontos;

/**
 * Created by Juan on 19/03/2018.
 */

public class Vendas2 {



    @JsonProperty("id_usuario")
    private Integer idUsuario;

    @JsonProperty("tipoDePontos") //vendas no plural
    private String tipoDePontos; //tipoDePontos

    @JsonProperty("valor")
    private String valor;

    @JsonProperty("quantidade")
    private String quantidade;




    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoDePontos() {
        return tipoDePontos;
    }

    public void setTipoDePontos(String tipoDePontos) {
        this.tipoDePontos = tipoDePontos;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

}
