package br.com.pointstore.Adapter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Arley on 28/03/2018.
 */

public class Vendas3 {

    @JsonProperty("id")
    private  String id;

    @JsonProperty("id_usuario_vendedor")
    private  String id_usuario_vendedor;

    @JsonProperty("id_usuario_comprador")
    private  String id_usuario_comprador;

    @JsonProperty("id_meus_pontos")
    private  String id_meus_pontos;

    @JsonProperty("titulo")
    private  String titulo;

    @JsonProperty("valor")
    private  String valor;

    @JsonProperty("status")
    private  String status;

    @JsonProperty("qtd_pontos")
    private  String qtd_pontos;

    @JsonProperty("nome")
    private  String nome;

    @JsonProperty("sobrenome")
    private  String sobrenome;

    @JsonProperty("cpf")
    private  String cpf;

    @JsonProperty("credito")
    private  String credito;

    @JsonProperty("email")
    private  String email;

    @JsonProperty("login")
    private  String login;

    @JsonProperty("senha")
    private  String senha;

    @JsonProperty("venda_id")
    private  String venda_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_usuario_vendedor() {
        return id_usuario_vendedor;
    }

    public void setId_usuario_vendedor(String id_usuario_vendedor) {
        this.id_usuario_vendedor = id_usuario_vendedor;
    }

    public String getId_usuario_comprador() {
        return id_usuario_comprador;
    }

    public void setId_usuario_comprador(String id_usuario_comprador) {
        this.id_usuario_comprador = id_usuario_comprador;
    }

    public String getId_meus_pontos() {
        return id_meus_pontos;
    }

    public void setId_meus_pontos(String id_meus_pontos) {
        this.id_meus_pontos = id_meus_pontos;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQtd_pontos() {
        return qtd_pontos;
    }

    public void setQtd_pontos(String qtd_pontos) {
        this.qtd_pontos = qtd_pontos;
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

    public String getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(String venda_id) {
        this.venda_id = venda_id;
    }
}