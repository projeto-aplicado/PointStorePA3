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
		em.persist(usuario);}
	
	public Usuario fazerLogin(Usuario usuario) {
        
		
		this.usuario = usuario;
		
		Usuario user = null;
        try {
            StringBuilder consulta = new StringBuilder("select u from Usuario u  "
            		+ "where u.login = :login and u.senha = :senha");
            javax.persistence.Query q = em.createQuery(consulta.toString());
            q.setParameter("login", this.usuario.getLogin());
            q.setParameter("senha", this.usuario.getSenha());
            user = (Usuario) q.getSingleResult();
       
        } catch (NoResultException e) {
           user = null;
           
          // System.out.println("Nenhum usuario encontrado");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return user;
    }
	
	
	public Usuario carregarUsuario(Long id) {
		
		return this.em.find(Usuario.class, id);	}
	
	public void atualizaUsuario(Usuario usuario) {
		
		em.merge(usuario);}

}
