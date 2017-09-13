package br.com.pointstore.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.R;
import br.com.pointstore.model.Loja;
import br.com.pointstore.model.MeusPontos;
import br.com.pointstore.model.Usuario;
import rest.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 02/04/2017.
 */

public class CadastrarPontos extends AppCompatActivity {

    private EditText editTextTipoPontos;
    private EditText editTextQtdPontos;

    private UsuarioService mUsuarioService;
    private Usuario usuario;
    private MeusPontos meusPontos;
    private Loja loja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pontos);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mUsuarioService = retrofit.create(UsuarioService.class);


        editTextTipoPontos = (EditText) findViewById(R.id.editTextTipoPonto);
        editTextQtdPontos = (EditText) findViewById(R.id.editTextQtdPontos);


    }


    public void cadastrarPontos (View v) {

        usuario = (Usuario) getIntent().getSerializableExtra("user");

        //loja = (Loja)this.editTextTipoPontos.getText().toString();

        //meusPontos = new MeusPontos(editTextTipoPontos.getText().toString(),editTextQtdPontos.getText().toString());

        if ((editTextTipoPontos.getText().length() > 0) && (editTextQtdPontos.getText().length() > 0) ){
            Intent cadastrarPontos = new Intent(this, ListarAnunciosActivity.class);
            startActivity(cadastrarPontos);

            Call<Usuario> userCall = mUsuarioService.createUser(usuario);
            userCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {


                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {


                }
            });

        } else {

            if (editTextTipoPontos.getText().length() <= 0){
                editTextTipoPontos.setError("Insira o Tipo de Ponto!");
            }
            if (editTextQtdPontos.getText().length() <= 0 ){
                editTextQtdPontos.setError("Insira a Quantidade de Pontos!");
            }


        }

    }

    public void listarAnuncio (View view){
        Intent listaranuncio = new Intent(this, ListarAnunciosActivity.class);
        startActivity(listaranuncio);
    }


}
