package br.com.unifor.PA3.DAO;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.unifor.PA3.entitys.Pontos;

@Stateless
public class PontosDAO {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Pontos pontos1;
	
	public void salvarPontos(Pontos pontos) {
		em.persist(pontos);
	}

	public Pontos carregarPontos(Long id) {

		return this.em.find(Pontos.class, id);
	}

	//public Collection<Pontos> buscarTodosPontos()
	public void buscarTodosPontos() {
		
		

		System.out.println("Testando o metodo");

		Collection<Pontos> pontoscollection;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pontos> cq = cb.createQuery(Pontos.class);
		Root<Pontos> rootEntry = cq.from(Pontos.class);
		CriteriaQuery<Pontos> all = cq.select(rootEntry);
		TypedQuery<Pontos> allQuery = em.createQuery(all);

		// return allQuery.getResultList();
		pontoscollection = allQuery.getResultList();

		if (pontoscollection.isEmpty()) {

			System.out.println("lista vazia");
			pontos1.setNome("LATAM");

			salvarPontos(pontos1);
			System.out.println("inserindo dados no banco");

		} else {

			System.out.println("lista lcom dados");

		}

		//return null;
	}

}
