package rest;

import java.util.List;

import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.UsuarioAlterarSenha;
import br.com.pointstore.Adapter.UsuarioCadastro;
import br.com.pointstore.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by FabricioMelo on 11/03/2017.
 */

public interface UsuarioService {
    /*

    @POST("/pointstorePA3/index.php/usuario")   
    Call<Usuario> createUser(@Body Usuario usuario);*/

    @POST("http://pointstore.herokuapp.com/index.php/usuario")
    Call<Usuario> createUser(@Body Usuario usuario);

    /*
    * By Arley: Criei essa chamada do serviço (createUserNoPoints)para cadastrar uma entidade Usuario do pacote adpater, levando em consideração que ela
    * carrega apenas informações simples do tipo nome,sobrenome,email,login e senha
    * */
    @POST("http://pointstore.herokuapp.com/index.php/usuario")
    Call<Usuario> createUserNoPoints (@Body UsuarioCadastro usuarioCadastro);

    /* nao editar esse metodo ate testar tudo esse éo metodo padrao
    @PUT("/pointstorePA3/index.php/login/usuario")
    Call<Usuario> updateUser(@Body Usuario usuario);*/


    @PUT("http://pointstore.herokuapp.com/index.php/login/usuario")
    Call<Menssagem> updateUser(@Body Usuario usuario);

    /*
    * Caputura usuario por ID
    * */

    @GET("http://pointstore.herokuapp.com/index.php/usuario/{id}")
    Call<Usuario> selecionaUmUsuarioID (@Body String idComprador);
}
