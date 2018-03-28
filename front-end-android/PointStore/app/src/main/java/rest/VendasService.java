package rest;

import java.util.List;

import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.Vendas2;
import br.com.pointstore.model.Vendas;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Juan on 19/03/2018.
 */

public interface VendasService {
    @POST("/pointstorePA3/index.php/venda")
    Call<Vendas> cadastrarVendas(@Body Vendas2 vendas);

    @POST("/pointstorePA3/index.php/venda")
    Call<Menssagem> msgvenda(@Body Vendas2 vendas);

    @GET("/pointstorePA3/index.php/venda/{id}")
    Call<List<Vendas>> listarVendas (@Path("id") String id);
}
