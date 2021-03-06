package rest;

import java.util.List;

import br.com.pointstore.Adapter.AtualizadorDeVendas;
import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.Vendas2;
import br.com.pointstore.Adapter.Vendas3;
import br.com.pointstore.Adapter.Vendas4;
import br.com.pointstore.model.Compra;
import br.com.pointstore.model.Vendas;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Juan on 19/03/2018.
 */

public interface VendasService {
    @POST("http://pointstore.herokuapp.com/index.php/venda")
    Call<Vendas> cadastrarVendas(@Body Vendas2 vendas);

    @POST("http://pointstore.herokuapp.com/index.php/venda")
    Call<Menssagem> msgvenda(@Body Vendas2 vendas);

    @GET("http://pointstore.herokuapp.com//index.php/venda/{id}")
    Call<List<Vendas>> listarVendas (@Path("id") String id);

    @PUT("http://pointstore.herokuapp.com/index.php/compra")
    Call<Menssagem> finalizarcompra (@Body Compra compra);

    /*Arley*/
    @GET("http://pointstore.herokuapp.com/index.php/venda/")
    Call<List<Vendas3>> listarTodasVendas ();

    /*Arley*/
    @GET("http://pointstore.herokuapp.com/index.php/compra/{id}")
    Call<List<Vendas4>> listarTodasComprasUsuario (@Path("id") String id);

    /*Arley*/
    @GET("http://pointstore.herokuapp.com/index.php/venda/gerenciarVendas/{id}")
    Call<List<Vendas3>> listarTodasAsVendasDoUsuarioPorId (@Path("id") String i);

    /*Arley*/
    @DELETE("http://pointstore.herokuapp.com/index.php/venda/excluirVendaId/{venda_id}")
    Call<Menssagem> excluirVenda (@Path("venda_id") String venda_id );


    @PUT("http://pointstore.herokuapp.com/index.php/venda/gerenciarVendas/")
        //Call<Menssagem> auterarPrecoDaVenda (@Body String valor, String venda_id);
    Call<Menssagem> auterarPrecoDaVenda (@Body AtualizadorDeVendas atualizadorDeVendas);

    @GET("http://pointstore.herokuapp.com/index.php/venda/historicoVendas/{id}")
    Call<List<Vendas3>> listarTodasVendasUsuario (@Path("id") String id);


}
