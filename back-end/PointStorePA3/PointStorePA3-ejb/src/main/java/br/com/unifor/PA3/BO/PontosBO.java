package br.com.unifor.PA3.BO;

import javax.ejb.EJB;

import br.com.unifor.PA3.DAO.PontosDAO;
import br.com.unifor.PA3.entitys.Pontos;

public class PontosBO {

	@EJB
	private PontosDAO pontosDAO;
	
	public void gravarPontos(Pontos pontos) {
		
		pontosDAO.salvarPontos(pontos);
		
	}
}
