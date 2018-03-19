package br.com.pointstore;

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

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.PontosMeusPontos;
import br.com.pointstore.model.Pontos;
import br.com.pointstore.model.Usuario;
import rest.PontosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Listar_pontos_cadastradros extends AppCompatActivity {

    private ListView listViewPontos;

    private ArrayList<Pontos> listaDePontos = new ArrayList<Pontos>();
    //private ArrayList<Pontos> listaDePontos = new ArrayList<Pontos>();
    private ArrayAdapter<Pontos> adaptadorLista;

    private  Pontos pontosSelecionado= new Pontos();

    private int adaptadorLayout = android.R.layout.simple_list_item_1;

    private TextView NomeSelecionadotextView;

    private Button button;

    private PontosService mPontosService;

    private String idUsuario;

    private ArrayList<Pontos> listDePontosRecebida = new ArrayList<Pontos>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pontos_cadastradros);


        listViewPontos =  (ListView) findViewById(R.id.listViewPontosCadastros);

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

        Call<List<PontosMeusPontos>> pontosMeusPontosCall = mPontosService.TodosmeusPontos(idUsuario);

       pontosMeusPontosCall.enqueue(new Callback<List<PontosMeusPontos>>() {
           @Override
           public void onResponse(Call<List<PontosMeusPontos>> call, Response<List<PontosMeusPontos>> response) {
               List<PontosMeusPontos>novalistaDePontos = response.body();
               final ArrayAdapter adaptadorListaResponse = new ArrayAdapter(Listar_pontos_cadastradros.this,
                       adaptadorLayout,novalistaDePontos);
               listViewPontos.setAdapter(adaptadorListaResponse);

               

           }

           @Override
           public void onFailure(Call<List<PontosMeusPontos>> call, Throwable t) {

           }
       });
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Listar_pontos_cadastradros.this, ListarAnunciosActivity.class);
                startActivity(home);
            }
        });*/





    }
}
