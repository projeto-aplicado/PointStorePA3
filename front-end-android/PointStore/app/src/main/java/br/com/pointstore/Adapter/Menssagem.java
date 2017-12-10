package br.com.pointstore.Adapter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Arley on 30/11/2017.
 */

public class Menssagem {

    @JsonProperty("mensagem")
    private String  mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
