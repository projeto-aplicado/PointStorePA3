package br.com.pointstore.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.Vendas2;
import br.com.pointstore.CadastrarSeusPontos;
import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.R;
import br.com.pointstore.model.Pontos;
import br.com.pointstore.model.Usuario;
import br.com.pointstore.model.Vendas;
import okio.Buffer;
import rest.UsuarioService;
import rest.VendasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 23/03/2017.
 */

public class CadastrarVendas extends AppCompatActivity {

    private EditText editTextQtdPontos;
    private EditText editTextValorPontos;
    private EditText editTextTipoPontos;

    private UsuarioService mUsuarioService;
    private Usuario usuario;
    private VendasService mVendasService;
    private Vendas2 vendas2;
    private String idUsuario;

    private ListView listViewPontos;
    private Pontos pontosSelecionado= new Pontos();
    private int adaptadorLayout = android.R.layout.simple_list_item_1;
    private TextView NomeSelecionadotextView;
    private Button BT_ANUNCIAR_VENDAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_vendas2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mVendasService = retrofit.create(VendasService.class);
        this.usuario = (Usuario) getIntent().getSerializableExtra("user");

        editTextTipoPontos = (EditText) findViewById(R.id.editTextTipoPontos);
        editTextQtdPontos = (EditText) findViewById(R.id.editTextQtdPontos);
        editTextValorPontos = (EditText) findViewById(R.id.editTextValorPontos);
        BT_ANUNCIAR_VENDAS = (Button)findViewById(R.id.buttonAnunciarVenda);
        listViewPontos =  (ListView) findViewById(R.id.listViewDePontos);
        NomeSelecionadotextView = (TextView) findViewById(R.id.pontoSelecionado);
    }

    public void cadastrarVendas (View v) {

            Vendas2 vendas2 = new Vendas2();

            vendas2.setQuantidade(editTextQtdPontos.getText().toString());
            vendas2.setValor(editTextValorPontos.getText().toString());
            vendas2.setTipoDePontos(editTextTipoPontos.getText().toString());
            vendas2.setIdUsuario(this.usuario.getIdUsuario());
            if (editTextTipoPontos.getText().toString() != "tam" || editTextTipoPontos.getText().toString() != "gol" ){

                Intent cadastrarVendas = new Intent(this, ListarAnunciosActivity.class);
                startActivity(cadastrarVendas);
                Call<Vendas> vendasCall = mVendasService.cadastrarVendas(vendas2);
                //Call<List<Menssagem>> vendasCall = mVendasService.cadastrarVendas(this.usuario.getIdUsuario().toString());

                vendasCall.enqueue(new Callback<Vendas>() {
                    @Override
                    public void onResponse(Call<Vendas> call, Response<Vendas> response) {
                        Vendas vendas = response.body();

                        //Toast.makeText(getApplication(), menssagem.getMensagem(),
                                //Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CadastrarVendas.this,ListarAnunciosActivity.class);

                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Vendas> call, Throwable t) {
                        Log.d("ERRO"," erro : " +t);
                    }
                });


            } else {
                Toast.makeText(this, "o ponto tem que ser escrito em minusculo", Toast.LENGTH_SHORT).show();
            }




    }

    public void listarAnuncio (View view){
        Intent listaranuncio = new Intent(this, ListarAnunciosActivity.class);
        startActivity(listaranuncio);
    }


    public void finalizarCompra(View view) {
        Intent finalizarcompra = new Intent(this, FinalizarCompra.class);
        startActivity(finalizarcompra);
    }

    public void cadastrarSeusPontos(View view) {
        Intent cadastrarseuspontos = new Intent (this, CadastrarSeusPontos.class);
        startActivity(cadastrarseuspontos);
    }
}
