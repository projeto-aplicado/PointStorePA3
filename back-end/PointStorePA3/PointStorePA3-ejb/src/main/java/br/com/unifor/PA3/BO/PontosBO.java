package br.com.unifor.PA3.BO;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;

import br.com.unifor.PA3.DAO.PontosDAO;
import br.com.unifor.PA3.entitys.Pontos;

@Stateless
public class PontosBO {

	@EJB
	private PontosDAO pontosDAO;

	public void gravarPontos(Pontos pontos) {

		pontosDAO.salvarPontos(pontos);

	}

	public void verificarBancoPontos() {
		
		pontosDAO.buscarTodosPontos();
	}
}
