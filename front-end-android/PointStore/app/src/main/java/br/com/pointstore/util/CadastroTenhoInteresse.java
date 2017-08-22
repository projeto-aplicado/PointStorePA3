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

public class CadastroTenhoInteresse extends AppCompatActivity {

    private EditText editTextInteressePonto1;
    private EditText editTextInteressePonto2;
    private EditText editTextInteressePonto3;

    private UsuarioService mUsuarioService;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tenho_interesse);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mUsuarioService = retrofit.create(UsuarioService.class);


        editTextInteressePonto1 = (EditText) findViewById(R.id. editTextInteressePonto1);
        editTextInteressePonto2 = (EditText) findViewById(R.id.editTextInteressePonto2);
        editTextInteressePonto3 = (EditText) findViewById(R.id.editTextInteressePonto3);
    }


    public void cadastroTenhoInteresse (View v) {

        //usuario = new Usuario(editTextInteressePonto1.getText().toString(),editTextInteressePonto2.getText().toString(),"3",editTextInteressePontos3.getText().toString());

        if ((editTextInteressePonto1.getText().length() > 0) && (editTextInteressePonto2.getText().length() > 0) && (editTextInteressePonto3.getText().length() > 0)){

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

            if (editTextInteressePonto1.getText().length() <= 0){
                editTextInteressePonto1.setError("Tipo de Ponto nº1 é obrigatório");
            }
            if (editTextInteressePonto2.getText().length() <= 0 ){
                editTextInteressePonto2.setError("Tipo de Ponto nº2 é obrigatório");
            }
            if (editTextInteressePonto3.getText().length() <= 0 ){
                editTextInteressePonto3.setError("Tipo de Ponto nº3 é obrigatório");
            }
        }


    }

    public void listarAnuncio (View view){
        Intent listaranuncio = new Intent(this, ListarAnunciosActivity.class);
        startActivity(listaranuncio);
    }


}
