package br.com.pointstore.Adapter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AtualizadorDeVendas {

    @JsonProperty("valor")
    private String valor;

    @JsonProperty("venda_id")
    private String venda_id;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(String venda_id) {
        this.venda_id = venda_id;
    }
}
