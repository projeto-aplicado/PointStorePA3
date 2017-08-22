package rest;

import br.com.pointstore.model.MeusPontos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Juan on 26/05/2017.
 */

public interface CadastroPontosService {

    @POST("/PointStoreWeb/rest/meuspontos")
    Call<MeusPontos> cadastrarPonto(@Body MeusPontos meusPontos);


}
