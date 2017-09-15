package br.com.unifor.PA3.entitys;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 7578849729765946537L;
	
	@Id
	@Column(name="id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome") 
	private String nome;
	
	@Column(name="sobrenome")
	private String sobrenome;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setId(Long id) {
		this.id = id;
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
