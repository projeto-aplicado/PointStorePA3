package br.com.pointstore.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.R;
import br.com.pointstore.model.Loja;
import br.com.pointstore.model.MeusPontos;
import br.com.pointstore.model.Usuario;
import rest.CadastroPontosService;
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

    private CadastroPontosService cadastroPontosService;
    private Usuario usuario;
    private MeusPontos meusPontos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pontos);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.17:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        cadastroPontosService = retrofit.create(CadastroPontosService.class);


        editTextTipoPontos = (EditText) findViewById(R.id.editTextTipoPonto);
        editTextQtdPontos = (EditText) findViewById(R.id.editTextQtdPontos);


    }


    public void cadastrarPontos (View v) {

        usuario = (Usuario) getIntent().getSerializableExtra("user");

        meusPontos = new MeusPontos(editTextQtdPontos.getText().toString(), editTextQtdPontos.getText().toString());

        if ((editTextTipoPontos.getText().length() > 0) && (editTextQtdPontos.getText().length() > 0) ){

            Call<MeusPontos> meusPontosCall = cadastroPontosService.cadastrarPonto(meusPontos);
            meusPontosCall.enqueue(new Callback<MeusPontos>() {
                @Override
                public void onResponse(Call<MeusPontos> call, Response<MeusPontos> response) {
                    Intent listaranuncio = new Intent(CadastrarPontos.this, ListarAnunciosActivity.class);
                    startActivity(listaranuncio);

                }

                @Override
                public void onFailure(Call<MeusPontos> call, Throwable t) {
                    Log.e("APP", t.getMessage());
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

}
