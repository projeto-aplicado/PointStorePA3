package rest;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.model.MeusPontos;
import br.com.pointstore.model.Pontos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Juan on 26/05/2017.
 */

public interface PontosService {

    /*
    @POST("/PointStoreWeb/rest/meuspontos")
    Call<MeusPontos> cadastrarPonto(@Body MeusPontos meusPontos);*/


    @GET("/pointstorePA3/index.php/meuspontos/{id}")

    Call<List<Pontos>> meusPontos (@Path("id") String id);





}
