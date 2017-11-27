package br.com.pointstore.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by FabricioMelo on 11/03/2017.
 */

public class Usuario implements Serializable {

    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String credito;
    private String email;
    private String login;
    private String senha;
    private Integer vendas;
    //private MeusPontos meusPontos;
    //private MinhasCompras minhasCompras;

    public Usuario (){
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, String sobrenome, String credito, String email, String cpf, String login, String senha) {//perfil e login
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.credito = credito;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, String sobrenome, String email, String login, String senha) { //cadastro

        this.login = login;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.credito = credito;
        this.vendas = vendas;
        this.nome = nome;
        this.cpf = cpf;
        //this.idUsuario = idUsuario;
        this.email = email;
        //this.meusPontos = meusPontos;
        //this.minhasCompras = minhasCompras;
    }


    public Usuario(Usuario caduser) { //Perfil

        this.login = caduser.getLogin();
        this.sobrenome = caduser.getSobrenome();
        this.senha = caduser.getSenha();
        this.credito = caduser.getCredito();
        this.nome = caduser.getNome();
        this.cpf = caduser.getCpf();
        //this.idUsuario = caduser.getIdUsuario();
        this.email = caduser.getEmail();
        //this.meusPontos = meusPontos;
        //this.minhasCompras = minhasCompras;
    }

    protected Usuario(Parcel in) {
        /*
        * Testar serializar o idUsuário*/
        //idUsuario = in.readInt();
        nome = in.readString();
        sobrenome = in.readString();
        cpf = in.readString();
        credito = in.readString();
        email = in.readString();
        login = in.readString();
        senha = in.readString();
    }




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
/*
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public MeusPontos getMeusPontos() {
        return meusPontos;
    }

    public void setMeusPontos(MeusPontos meusPontos) {
        this.meusPontos = meusPontos;
    }

    public MinhasCompras getMinhasCompras() {
        return minhasCompras;
    }

    public void setMinhasCompras(MinhasCompras minhasCompras) {
        this.minhasCompras = minhasCompras;
    }

    */

}
