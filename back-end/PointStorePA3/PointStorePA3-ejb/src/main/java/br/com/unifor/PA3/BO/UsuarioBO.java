package br.com.unifor.PA3.BO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.unifor.PA3.DAO.UsuarioDAO;
import br.com.unifor.PA3.entitys.Usuario;

@Stateless
public class UsuarioBO {
		
	@EJB
	private UsuarioDAO usuarioDAO;
	
	
	
	public void gravarUsuario(Usuario usuario) {
		usuarioDAO.salvarUsuario(usuario);
	}
	
	
	public Usuario fazerLogin(Usuario usuario) {
		
		
		return usuarioDAO.logarUsuario(usuario);
	}
	
	
	
	
	
}
