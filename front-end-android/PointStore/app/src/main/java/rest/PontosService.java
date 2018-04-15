package rest;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.PontosMeusPontos;
import br.com.pointstore.model.MeusPontos;
import br.com.pointstore.model.Pontos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Juan on 26/05/2017.
 */

public interface PontosService {

    /*
    @POST("/PointStoreWeb/rest/meuspontos")
    Call<MeusPontos> cadastrarPonto(@Body MeusPontos meusPontos);*/

    /*Faz uma busca de pontos com id do usuário,seleciona TODOS os pontos do usuário*/
    @GET("http://pointstore.herokuapp.com/index.php/meuspontos/{id}")
    Call<List<Pontos>> meusPontos (@Path("id") String id);

    /*Faz uma busca de pontos com id do usuário,seleciona TODOS os pontos do usuário
    * para ser listado dentro da atividade MeusPontos,diferente do buscar pontos para cadastrar quantidade*/
    @GET("http://pointstore.herokuapp.com/index.php/meuspontos/{id}")
    Call<List<PontosMeusPontos>> TodosmeusPontos (@Path("id") String id);


    /*Faz o envio do Objeto do tipo Ponto, carregando seu ID, e dados para serem alterados*/
    @PUT("http://pointstore.herokuapp.com/index.php/meuspontos/atualizar")
    Call<Menssagem> atualizarMeusPontos (@Body Pontos pontos);





}
