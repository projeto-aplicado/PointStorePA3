package rest;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.PontosMeusPontos;
import br.com.pointstore.Adapter.Vendas2;
import br.com.pointstore.model.MeusPontos;
import br.com.pointstore.model.Pontos;
import br.com.pointstore.model.Usuario;
import br.com.pointstore.model.Vendas;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Juan on 19/03/2018.
 */

public interface VendasService {
    @POST("/pointstorePA3/index.php/venda")
    Call<Vendas> cadastrarVendas(@Body Vendas2 vendas);

    

    @GET("/pointstorePA3/index.php/venda/{id}")
    Call<List<Vendas>> listarVendas (@Path("id") String id);
}
