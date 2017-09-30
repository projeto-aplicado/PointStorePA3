package br.com.unifor.PA3.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.unifor.PA3.entitys.Pontos;

@Stateless
public class PontosDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvarPontos(Pontos pontos){
		em.persist(pontos);
		}
	
	public Pontos carregarPontos(Long id) {
		
		return this.em.find(Pontos.class, id);	}

}
