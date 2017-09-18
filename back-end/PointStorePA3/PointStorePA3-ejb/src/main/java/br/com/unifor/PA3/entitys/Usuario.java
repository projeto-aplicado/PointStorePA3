package br.com.unifor.PA3.entitys;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Stateless
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 7578849729765946537L;
	
	@Id
	@Column(name="id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name="login")
	private String login;
	
	
	@Column(name="nome") 
	private String nome;
	

	
	@Column(name="sobrenome")
	private String sobrenome;
	
	@Column(name="cpf")
	private int cpf;
	
	
	@Column(name="credito") 
	private  double credito;
	
	@Column(name="senha")
	private double senha;
	
	@Column (name="vendas")
	private int vendas;
	
	/*
	@OneToMany
	@Column (name="lista_de_pontos")
	private Collection<Pontos> meusPontos;*/
	
	

	/*
	public Collection<Pontos> getMeusPontos() {
		return meusPontos;
	}

	public void setMeusPontos(Collection<Pontos> meusPontos) {
		this.meusPontos = meusPontos;
	}

	public Collection<Pontos> adcionarMeusPontos(Pontos pontos){
		this.meusPontos.add(pontos);
		
		return meusPontos;
		
	}*/
	
	
	

	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
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
	
	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public double getCredito() {
		return credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public double getSenha() {
		return senha;
	}

	public void setSenha(double senha) {
		this.senha = senha;
	}

	
	public int getVendas() {
		return vendas;
	}

	public void setVendas(int vendas) {
		this.vendas = vendas;
	}
	
	
	
	

}
