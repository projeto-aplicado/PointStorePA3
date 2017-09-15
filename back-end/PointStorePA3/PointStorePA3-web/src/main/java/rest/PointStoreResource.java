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
	@Produces(MediaType.APPLICATION_JSON)
	public Response testejson(){
		usuario.setNome("TesteJson");
		usuario.setSobrenome("testando json agora");
		return Response.ok(usuario).build();
		
		
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/caduser/{nome}/{sobrenome}/{senha}")
	public Response cadusuario(@PathParam("nome") String nome,@PathParam("sobrenome") String sobrenome,
	@PathParam("senha") Double senha){
	 
		
		
		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setSenha(senha);
		
		usuarioBO.gravarUsuario(usuario);
		
		System.out.println("usuário "+usuario.getNome()+" cadastrado");
		System.out.println("sobrenome "+usuario.getSobrenome()+" cadastrado");
		System.out.println("senha  "+usuario.getSenha()+" cadastrado");
		System.out.println("quantidade de vendas "+usuario.getVendas()+" cadastrado");
		
		
		
		return Response.ok(usuario).build();
		
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/{nome}/{senha}")
	public Response login(@PathParam("nome") String nome,@PathParam("senha") Double senha) {
		
		usuario.setNome(nome);
		usuario.setSenha(senha);
		
		
		
		usuario = usuarioBO.fazerLogin(usuario);
		
		System.out.println("usuário "+usuario.getNome()+"cadastrado");
		return Response.ok("Tentando login").build();
	}
	
	
}
