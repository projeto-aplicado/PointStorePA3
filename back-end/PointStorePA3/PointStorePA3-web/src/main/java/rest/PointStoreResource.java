package rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Param;

import br.com.unifor.PA3.BO.UsuarioBO;

import br.com.unifor.PA3.entitys.Usuario;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@RequestScoped
@Path("/pointstore")
public class PointStoreResource {
	@Inject
	private UsuarioBO usuarioBO;
	@Inject
	private Usuario usuario;
	
	@GET
	@Produces("application/json")
	public Response testejson(){
		
		usuario.setNome("Testando o Json");
		usuario.setCpf(123456789);
		return Response.ok(usuario).build();}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/caduser")
	public Response caduser(Usuario usuario){
	 	
		usuarioBO.gravarUsuario(usuario);
		
		System.out.println("usuário "+usuario.getNome()+" cadastrado");
		System.out.println("sobrenome "+usuario.getSobrenome()+" cadastrado");
		System.out.println("senha  "+usuario.getSenha()+" cadastrado");
		System.out.println("quantidade de vendas "+usuario.getVendas()+" cadastrado");

		return Response.ok(usuario).build();}
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(Usuario usuario) {
		return Response.ok(usuarioBO.fazerLogin(usuario)).build();}
	
	
}
