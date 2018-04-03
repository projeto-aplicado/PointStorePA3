package br.com.pointstore.model;

import android.content.Intent;

/**
 * Created by juant on 02/04/2018.
 */

public class Compra {

    private String id_usuario;
    private String venda_id;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(String venda_id) {
        this.venda_id = venda_id;
    }
}
