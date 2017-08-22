package br.com.pointstore.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.R;
import br.com.pointstore.model.Usuario;
import rest.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 23/03/2017.
 */

public class CadastrarVendas extends AppCompatActivity {

    private EditText editTextTipoPontos;
    private EditText editTextQtdPontos;
    private EditText editTextValorPontos;

    private UsuarioService mUsuarioService;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_vendas);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mUsuarioService = retrofit.create(UsuarioService.class);


        editTextTipoPontos = (EditText) findViewById(R.id. editTextTipoPonto);
        editTextQtdPontos = (EditText) findViewById(R.id.editTextQtdPontos);
        editTextValorPontos = (EditText) findViewById(R.id.editTextValorPontos);
    }

    public void cadastrarPontos (View v) {

        //usuario = new Usuario(editTextTipoPontos.getText().toString(),editTextQtdPontos.getText().toString(),"3",editTextValorPontos.getText().toString());

        if ((editTextTipoPontos.getText().length() > 0) && (editTextQtdPontos.getText().length() > 0) && (editTextValorPontos.getText().length() > 0)){

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
                editTextTipoPontos.setError("Campo Tipo de Pontos é obrigatório");
            }
            if (editTextQtdPontos.getText().length() <= 0 ){
                editTextQtdPontos.setError("Campo Quantidade de Pontos é obrigatório");
            }
            if (editTextValorPontos.getText().length() <= 0 ){
                editTextValorPontos.setError("Campo Valor de Pontos é obrigatório");
            }

        }

    }

    public void listarAnuncio (View view){
        Intent listaranuncio = new Intent(this, ListarAnunciosActivity.class);
        startActivity(listaranuncio);
    }


}
