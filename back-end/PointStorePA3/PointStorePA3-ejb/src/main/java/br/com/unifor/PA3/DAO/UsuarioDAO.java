package br.com.unifor.PA3.DAO;

import java.awt.List;
import java.nio.file.attribute.AclEntry.Builder;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.unifor.PA3.entitys.Usuario;

@Stateless
public class UsuarioDAO {
	

	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Usuario usuario;
	
	public void salvarUsuario(Usuario usuario){
		em.persist(usuario);
		
		}
	
	
	/*public Usuario efetuarLogin(String nome, double senha) {
		
	try {
		em.getTransaction().begin();
	Query query= (Query) em.createQuery("from usuario  where nome = ?1 and senha = ?2");
		
		((javax.persistence.Query) query).setParameter(1, nome);
		((javax.persistence.Query) query).setParameter(2, senha);
		((javax.persistence.Query) query).setMaxResults(1);
		
		Usuario novousuario = new Usuario();
		
		Collection<Usuario>usuarios = ((javax.persistence.Query) query).getResultList();
		em.getTransaction().commit();
		
		
	} catch (Exception e) {
		 em.getTransaction().rollback();
	}
			
	
      return null;
		
		
	}*/
	
	public Usuario logarUsuario(Usuario userLogin){
		Usuario usuarioDetached = (Usuario) this.em.createQuery("from usuario  where nome = :nome and senha = :senha")
				  .setParameter("nome", userLogin.getNome() ).setParameter("senha", userLogin.getSenha()).getSingleResult();
		
		
		System.out.println("usuario: " + userLogin.getNome() + " senha: " + userLogin.getSenha());
		
		
		return usuarioDetached;
	}
	
	
	
	
	
	
	public Usuario carregarUsuario(Long id) {
		
		return this.em.find(Usuario.class, id);	}
	
	public void atualizaUsuario(Usuario usuario) {
		
		em.merge(usuario);}

}
