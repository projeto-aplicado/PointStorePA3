package rest;

import java.util.Collection;

import br.com.pointstore.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Romulo Xavier on 21/03/17.
 */

public interface LoginService {

    @POST("/PointStoreWeb/rest/usuarioLogin")
    Call<Usuario> loginUser(@Body Usuario usuario);

    @GET("/PointStoreWeb/rest/usuarioLogin")
    Call<Collection<Usuario>> logar();

    @PUT("/PointStoreWeb/rest/usuarioLogin/{email}")
    Call<Usuario> atualizaSenha(@Body Usuario usuario, @Path("email") String email);

}
