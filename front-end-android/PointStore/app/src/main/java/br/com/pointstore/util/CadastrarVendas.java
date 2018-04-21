package br.com.pointstore.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.ListaDePontosAdapter;
import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.Vendas2;
import br.com.pointstore.CadastrarSeusPontos;
import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.R;
import br.com.pointstore.model.Pontos;
import br.com.pointstore.model.Usuario;
import rest.PontosService;
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

    private UsuarioService mUsuarioService;
    private Usuario usuario;
    private VendasService mVendasService;
    private PontosService mPontosService;
    private Vendas2 vendas2;
    private String idUsuario;


    private ArrayList<Pontos> listaDePontos = new ArrayList<Pontos>();
    private Pontos pontosSelecionado= new Pontos();
    private int adaptadorLayout = android.R.layout.simple_list_item_1;
    private TextView NomeSelecionadotextView;
    private Button BT_ANUNCIAR_VENDAS;

    private Spinner spinner;
    private List<String> pontos = new ArrayList<String>();
    private String ponto;

    /**/
    private ListView listViewPontos;
    /**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_vendas2);

        listViewPontos =  (ListView) findViewById(R.id.listViewDePontos);
        //NomeSelecionadotextView = (TextView) findViewById(R.id.pontoSelecionadovenda);
        BT_ANUNCIAR_VENDAS = (Button)findViewById(R.id.buttonAnunciarVenda);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        pontos.add("Tipo de Ponto:");
        pontos.add("Tam");
        pontos.add("Gol");

        spinner = (Spinner) findViewById(R.id.spinnervenda);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pontos);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                ponto = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                Snackbar.make(findViewById(R.id.buttonAnunciarVenda), ponto + " Selecionado", Snackbar.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mVendasService = retrofit.create(VendasService.class);
        this.usuario = (Usuario) getIntent().getSerializableExtra("user");

        this.mPontosService = retrofit.create(PontosService.class);
        /*Recebendo por intent o ide de usuário  para fazer busca*/
        idUsuario = this.usuario.getIdUsuario().toString();

        Call<List<Pontos>> pontosCall = mPontosService.meusPontos(idUsuario);

        pontosCall.enqueue(new Callback<List<Pontos>>() {
            @Override
            public void onResponse(Call<List<Pontos>> call, Response<List<Pontos>> response) {


                List<Pontos>novalistaDePontos = response.body();
                ArrayList<Pontos> arrayListPontos = new ArrayList<>();
                arrayListPontos = (ArrayList<Pontos>) response.body();
                //if ((!response.isSuccessful())){ return;}
                /*
                É preciso saber que toda operação feita dentro do metodo  on response, precisa ter continuidade aqui
                * como por exemplo, tratar evento de click, evento de cadastrar qualquer dado qu seja exbido ou carregao pelo retrofit
                *
                * Evento de botão dentre outros serão implementados dentro desse onresponse
                * */
                /*
                final ArrayAdapter adaptadorListaResponse = new ArrayAdapter(CadastrarSeusPontos.this,
                        adaptadorLayout,novalistaDePontos); */

                final ListaDePontosAdapter adaptadorListaResponse =
                        new ListaDePontosAdapter(CadastrarVendas.this,
                                R.layout.adapter_view_pontos_layout,arrayListPontos);
                listViewPontos.setAdapter(adaptadorListaResponse);


            }

            @Override
            public void onFailure(Call<List<Pontos>> call, Throwable t) {

            }
        });


        editTextQtdPontos = (EditText) findViewById(R.id.editTextQtdPontos);
        editTextValorPontos = (EditText) findViewById(R.id.editTextValorPontos);


    }

    public void cadastrarVendas (View v) {

        final Vendas2 vendas2 = new Vendas2();

        vendas2.setQuantidade(editTextQtdPontos.getText().toString());
        vendas2.setValor(editTextValorPontos.getText().toString());
        vendas2.setTipoDePontos(ponto);
        vendas2.setIdUsuario(this.usuario.getIdUsuario());

        //Intent cadastrarVendas = new Intent(this, ListarAnunciosActivity.class);
        //startActivity(cadastrarVendas);
        //Call<Vendas3> vendasCall = mVendasService.cadastrarVendas(vendas2);
        if ((editTextQtdPontos.getText().length() > 0) && ((editTextValorPontos.getText().length() > 0))) {

            Call<Menssagem> menssagemCall = mVendasService.msgvenda(vendas2);

            menssagemCall.enqueue(new Callback<Menssagem>() {
                @Override
                public void onResponse(Call<Menssagem> call, Response<Menssagem> response) {
                    Menssagem menssagem = response.body();
                    if (menssagem != null ) {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, " : "+menssagem.getMensagem(), Toast.LENGTH_SHORT);
                        toast.show();

                        Intent cadastrarVendas = new Intent(CadastrarVendas.this, ListarAnunciosActivity.class);
                        startActivity(cadastrarVendas);

                    /*
                    Context context1 = getApplicationContext();
                    Toast toast1 = Toast.makeText(context1, "Quantidade de pontos : "+ vendas2.getQuantidade(), Toast.LENGTH_SHORT);
                    toast.show();
                    */
                        this.finish();
                    } else {

                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "Venda não cadastrada" , Toast.LENGTH_SHORT);
                        toast.show();

                    }

                }

                private void finish() {
                }

                @Override
                public void onFailure(Call<Menssagem> call, Throwable t) {

                }
            });

        } else {
            if ((editTextQtdPontos.getText().length() <= 0)) {
                editTextQtdPontos.setError("Quantidade de Pontos Vazio!");
            }  if ((editTextValorPontos.getText().length() <= 0)) {
                editTextValorPontos.setError("Valor Vazio!");
            }
        }
    }



    public void cadastrarSeusPontos(View view) {
        Intent cadastrarseuspontos = new Intent (this, CadastrarSeusPontos.class);
        startActivity(cadastrarseuspontos);
    }
}