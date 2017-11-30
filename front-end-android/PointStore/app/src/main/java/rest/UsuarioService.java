package rest;

import java.util.List;

import br.com.pointstore.Adapter.UsuarioCadastro;
import br.com.pointstore.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by FabricioMelo on 11/03/2017.
 */

public interface UsuarioService {
    /*

    @POST("/pointstorePA3/index.php/usuario")   
    Call<Usuario> createUser(@Body Usuario usuario);*/

    @POST("/pointstorePA3/index.php/usuario")
    Call<Usuario> createUser(@Body Usuario usuario);

    /*
    * By Arley: Criei essa chamada do serviço (createUserNoPoints)para cadastrar uma entidade Usuario do pacote adpater, levando em consideração que ela
    * carrega apenas informações simples do tipo nome,sobrenome,email,login e senha
    * */
    @POST("/pointstorePA3/index.php/usuario")
    Call<Usuario> createUserNoPoints (@Body UsuarioCadastro usuarioCadastro);

    @PUT("/PointStoreWeb/rest/usuario")
    Call<Usuario> updateUser(@Body Usuario usuario);
}
