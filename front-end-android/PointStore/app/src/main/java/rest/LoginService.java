package rest;

import java.util.Collection;

import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.UsuarioAlterarSenha;
import br.com.pointstore.Adapter.UsuarioLogin;
import br.com.pointstore.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Romulo Xavier on 21/03/17.
 */

public interface LoginService {

    /*
    @POST("pointstorePA3/index.php/login")
    Call<Usuario> loginUser(@Body Usuario usuario);*/

/*    @POST("pointstorePA3/index.php/login")
    Call<Usuario> loginUser(@Body UsuarioLogin usuarioLogin);
*/
/*
    @POST("pointstorePA3/index.php/login")
    Call<Usuario> loginUser(@Body  String login,@Body String senha);*/

    @POST("pointstorePA3/index.php/login")
    Call<Usuario> loginUser(@Body UsuarioLogin usuarioLogin);




    @GET("/PointStoreWeb/rest/usuarioLogin")
    Call<Collection<Usuario>> logar();

   /* @PUT("/PointStoreWeb/rest/usuarioLogin/{email}")
    Call<Usuario> atualizaSenha(@Body Usuario usuario, @Path("email") String email);*/


    @PUT("pointstorePA3/index.php/usuario/senha")
    Call<Menssagem> atualizaSenha(@Body UsuarioAlterarSenha usuarioAlterarSenha);

}
