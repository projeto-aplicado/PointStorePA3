package rest;

import java.util.List;

import br.com.pointstore.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by FabricioMelo on 11/03/2017.
 */

public interface UsuarioService {

    @POST("/PointStoreWeb/rest/usuario")
    Call<Usuario> createUser(@Body Usuario usuario);

    @PUT("/PointStoreWeb/rest/usuario")
    Call<Usuario> updateUser(@Body Usuario usuario);
}
