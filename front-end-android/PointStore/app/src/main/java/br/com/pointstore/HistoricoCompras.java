package br.com.pointstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.ListaDeComprasAdapter;
import br.com.pointstore.Adapter.ListaDeVendasAdapter;
import br.com.pointstore.Adapter.Vendas3;
import br.com.pointstore.Adapter.Vendas4;
import br.com.pointstore.model.Vendas;
import rest.VendasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HistoricoCompras extends AppCompatActivity {
    private ListView listviewdecompras;
    private ArrayList<Vendas4> listaDeCompras = new ArrayList<>();
    private VendasService mVendasService;
    private String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_compras);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.mVendasService = retrofit.create(VendasService.class);
        Bundle bundle = getIntent().getExtras();
        idUsuario =  bundle.getString("id_usuario");

        listviewdecompras = (ListView) findViewById(R.id.listviewdecompras);


        Call<List<Vendas4>> listarTodasComprasUsuario = mVendasService.listarTodasComprasUsuario(idUsuario);

        listarTodasComprasUsuario.enqueue(new Callback<List<Vendas4>>() {
            @Override
            public void onResponse(Call<List<Vendas4>> call, Response<List<Vendas4>> response) {

                listaDeCompras = (ArrayList<Vendas4>) response.body();

                if(listaDeCompras.isEmpty()){


                    AlertDialog.Builder adb = new AlertDialog.Builder(HistoricoCompras.this);
                    adb.setTitle("ATENÇÃO !");
                    adb.setMessage("Seu histórico de compras está vazio!");

                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(HistoricoCompras.this, "Você clicou no botão Ok", Toast.LENGTH_SHORT).show();
                            Intent finalizarCompra = new Intent(HistoricoCompras.this, ListarAnunciosActivity.class);
                            startActivity(finalizarCompra);
                        }
                    });
                    adb.show();


                }else {
                    final ListaDeComprasAdapter adaptadorListaResponse =
                            new ListaDeComprasAdapter(HistoricoCompras.this,
                                    R.layout.adapter_view_compras_layout,listaDeCompras);

                    listviewdecompras.setAdapter(adaptadorListaResponse);

                }



            }

            @Override
            public void onFailure(Call<List<Vendas4>> call, Throwable t) {
                String TAG_E = "Erro na Requisição";
                Log.v(TAG_E, ""+t);
                Toast.makeText(getApplication(), "ERRO "+t,
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}
