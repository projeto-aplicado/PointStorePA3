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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.model.Pontos;
import rest.PontosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CadastrarSeusPontos extends AppCompatActivity {

    private ListView listViewPontos;

    private ArrayList<Pontos> listaDePontos = new ArrayList<Pontos>();

    /*
    * Arraylist de pontos para receber da chamada pontos2
    * */

    private List<Pontos>  receberPontosDoUsuario;

    private ArrayAdapter<Pontos> adaptadorLista;

    private  Pontos pontosSelecionado= new Pontos();

    private int adaptadorLayout = android.R.layout.simple_list_item_1;

    private TextView NomeSelecionadotextView;

    private Button button;

    private PontosService mPontosService;

    private String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pontos2);


        listViewPontos =  (ListView) findViewById(R.id.listViewDePontos);

        NomeSelecionadotextView = (TextView) findViewById(R.id.pontoSelecionado);

        button = (Button)findViewById(R.id.buttonGravarDados);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.mPontosService = retrofit.create(PontosService.class);
        Bundle bundle = getIntent().getExtras();

        /*Recebendo por intent o ide de usuário  para fazer busca*/
        idUsuario = (String) bundle.getString("id_usuario");

        Log.d("CREATION","id do usuário seleciona é : "+idUsuario);



        /*------------------------------------------------------------------*/

        Call<List<Pontos>> pontosCall = mPontosService.meusPontos(idUsuario);


        pontosCall.enqueue(new Callback<List<Pontos>>() {
            @Override
            public void onResponse(Call<List<Pontos>> call, Response<List<Pontos>> response) {
                List<Pontos> meusPontosBanco = response.body();



                Log.d("CREATION"," Ponto selecionado : "+meusPontosBanco.get(1).gettipoDePonto());
                Log.d("CREATION"," Tamanho da List de Pontos : "+meusPontosBanco.size());

            }

            @Override
            public void onFailure(Call<List<Pontos>> call, Throwable t) {
                Log.d("ERRO"," erro : " +t);
            }
        });

        /*-------------------------------------------------------------------------*/

        adaptadorLista = new ArrayAdapter<Pontos>(this, adaptadorLayout, listaDePontos);

        listViewPontos.setAdapter(adaptadorLista);


        listViewPontos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pontosSelecionado = adaptadorLista.getItem(i);

               // Toast.makeText(getApplication(), "Ponto selecionado : "
                       // + pontosSelecionado.getNome(), Toast.LENGTH_LONG).show();

                /*Snackbar.make(findViewById(R.id.buttonGravarDados), pontosSelecionado.getNome(), Snackbar.LENGTH_SHORT).show();

                NomeSelecionadotextView.setText(pontosSelecionado.getNome());*/
            }




        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CadastrarSeusPontos.this,ListarAnunciosActivity.class);

                startActivity(intent);

            }
        });
    }
}
