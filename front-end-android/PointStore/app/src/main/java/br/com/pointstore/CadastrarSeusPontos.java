package br.com.pointstore;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.ListaDePontosAdapter;
import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.model.Pontos;
import rest.PontosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CadastrarSeusPontos extends AppCompatActivity {

    private ListView listViewPontos;
    private List<Pontos>  receberPontosDoUsuario;
    private  Pontos pontosSelecionado= new Pontos();
    private int adaptadorLayout = android.R.layout.simple_list_item_1;
    private TextView NomeSelecionadotextView;
    private Button BT_GRAVAR_DADOS;
    private EditText Ed_quantidade;
    private PontosService mPontosService;
    private String idUsuario;

    public void restartActivity(){
        Intent mIntent = getIntent();
        finish(); startActivity(mIntent); }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pontos2);


        listViewPontos =  (ListView) findViewById(R.id.listViewDePontos);

        NomeSelecionadotextView = (TextView) findViewById(R.id.pontoSelecionado);

        BT_GRAVAR_DADOS = (Button)findViewById(R.id.buttonGravarDados);

        Ed_quantidade = (EditText) findViewById(R.id.ed_quantidade);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.mPontosService = retrofit.create(PontosService.class);
        Bundle bundle = getIntent().getExtras();

        /*Recebendo por intent o ide de usuário  para fazer busca*/
        idUsuario = (String) bundle.getString("id_usuario");

        Log.d("CREATION","id do usuário seleciona é : "+idUsuario);

        /*
        * Faz uma solicitação pelo mPontosService,passando como parametro "idUsuario"
        * e recebe uma List de Objeto Pontos  para jogar dentro de um listview
        * */
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
                        new ListaDePontosAdapter(CadastrarSeusPontos.this,
                                R.layout.adapter_view_pontos_layout,arrayListPontos);

                listViewPontos.setAdapter(adaptadorListaResponse);


                listViewPontos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        pontosSelecionado = (Pontos) adaptadorListaResponse.getItem(i);



                        Toast.makeText(getApplication(), "Ponto selecionado : "
                        + pontosSelecionado.gettipoDePonto()+" id do ponto selecionado : "+pontosSelecionado.getId(),
                                Toast.LENGTH_SHORT).show();



                        NomeSelecionadotextView.setText(pontosSelecionado.gettipoDePonto());
                        //


                        //
                        BT_GRAVAR_DADOS.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Captura a quantidade de pontos indicada no campo,depois seta o valor
                                //no objeto "pontosSelecionado" e depois envia para ser atualizado

                                String quantidade = Ed_quantidade.getText().toString();

                                pontosSelecionado.setquantidadePonto(quantidade);

                                final Call<Menssagem> menssagemCall = mPontosService.atualizarMeusPontos(pontosSelecionado);

                                menssagemCall.enqueue(new Callback<Menssagem>() {
                                    @Override
                                    public void onResponse(Call<Menssagem> call, Response<Menssagem> response) {

                                        Menssagem menssagem = response.body();


                                        Toast.makeText(getApplication(), menssagem.getMensagem(),
                                                Toast.LENGTH_SHORT).show();

                                        restartActivity();
                                        /*

                                        Intent intent = new Intent(CadastrarSeusPontos.this,ListarAnunciosActivity.class);

                                        startActivity(intent);*/

                                    }

                                    @Override
                                    public void onFailure(Call<Menssagem> call, Throwable t) {   } });

                                    }
                                    });


                        }


                });

            }


            @Override
            public void onFailure(Call<List<Pontos>> call, Throwable t) {
                Log.d("ERRO"," erro : " +t);
            }
        });





    }



}
