package br.com.pointstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.ListaDeComprasAdapter;
import br.com.pointstore.Adapter.ListaDeMinhasVendasAdapter;
import br.com.pointstore.Adapter.Vendas3;
import br.com.pointstore.Adapter.Vendas4;
import rest.VendasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HistoricoVendas extends AppCompatActivity {
    private ListView listviewdevendas;
    private ArrayList<Vendas3> listaDeVendas = new ArrayList<>();
    private VendasService mVendasService;
    private String idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_vendas);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.mVendasService = retrofit.create(VendasService.class);
        Bundle bundle = getIntent().getExtras();
        idUsuario =  bundle.getString("id_usuario");

        listviewdevendas = (ListView) findViewById(R.id.listviewdecompras);


        Call<List<Vendas3>> listarTodasVendasUsuario = mVendasService.listarTodasVendasUsuario(idUsuario);

        listarTodasVendasUsuario.enqueue(new Callback<List<Vendas3>>() {
            @Override
            public void onResponse(Call<List<Vendas3>> call, Response<List<Vendas3>> response) {

                listaDeVendas = (ArrayList<Vendas3>) response.body();

                if(listaDeVendas.isEmpty()){


                    AlertDialog.Builder adb = new AlertDialog.Builder(HistoricoVendas.this);
                    adb.setTitle("ATENÇÃO !");
                    adb.setMessage("Seu histórico de vendas está vazio!");

                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(HistoricoCompras.this, "Você clicou no botão Ok", Toast.LENGTH_SHORT).show();
                            Intent finalizarCompra = new Intent(HistoricoVendas.this, ListarAnunciosActivity.class);
                            startActivity(finalizarCompra);
                        }
                    });
                    adb.show();


                }else {
                    final ListaDeMinhasVendasAdapter adaptadorListaResponse =
                            new ListaDeMinhasVendasAdapter(HistoricoVendas.this, R.layout.adapter_view_vendas_layout,listaDeVendas);

                    listviewdevendas.setAdapter(adaptadorListaResponse);

                }



            }

            @Override
            public void onFailure(Call<List<Vendas3>> call, Throwable t) {
                String TAG_E = "Erro na Requisição";
                Log.v(TAG_E, ""+t);
                Toast.makeText(getApplication(), "ERRO "+t,
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}
