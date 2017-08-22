package br.com.pointstore.model;

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

    private MeusPontos meusPontos;
    private MinhasCompras minhasCompras;
    private MinhasVendas minhasVenda;
    private MinhasQualificacoes minhasQualificacoes;

    public Usuario() {

    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, String sobrenome, String credito, String email, String cpf, String login, String senha) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.credito = credito;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(Integer idUsuario, String nome, String sobrenome, String credito, String email, String cpf, String login, String senha, MeusPontos meusPontos, MinhasCompras minhasCompras, MinhasVendas minhasVenda, MinhasQualificacoes minhasQualificacoes) {

        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.credito = credito;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.meusPontos = meusPontos;
        this.minhasCompras = minhasCompras;
        this.minhasVenda = minhasVenda;
        this.minhasQualificacoes = minhasQualificacoes;
    }

    protected Usuario(Parcel in) {
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

    public MinhasVendas getMinhasVenda() {
        return minhasVenda;
    }

    public void setMinhasVenda(MinhasVendas minhasVenda) {
        this.minhasVenda = minhasVenda;
    }

    public MinhasQualificacoes getMinhasQualificacoes() {
        return minhasQualificacoes;
    }

    public void setMinhasQualificacoes(MinhasQualificacoes minhasQualificacoes) {
        this.minhasQualificacoes = minhasQualificacoes;
    }

}
