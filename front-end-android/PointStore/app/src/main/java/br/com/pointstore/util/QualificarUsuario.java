package br.com.pointstore.util;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.model.Usuario;
import br.com.pointstore.R;
import br.com.pointstore.model.Usuario;
import rest.LoginService;
import rest.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 18/05/2017.
 */

class QualificarUsuario extends AppCompatActivity {


    private Usuario usuario;
    private Button BtnAvaliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualificar_usuario);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        BtnAvaliar = (Button) findViewById(R.id.BtnAvaliar);


        BtnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar mySnackbar = Snackbar.make( v,
                  //      "Obrigado pela Avaliação!", Snackbar.LENGTH_SHORT);
                //mySnackbar.show();

                Toast.makeText(QualificarUsuario.this, "Obrigado pela Avaliação!", Toast.LENGTH_SHORT).show();

                Intent listaranuncio = new Intent(QualificarUsuario.this, ListarAnunciosActivity.class);
                startActivity(listaranuncio);

            }

        });

    }

    public void onClick(View view) {
        Intent listaranuncio = new Intent(this, ListarAnunciosActivity.class);
        startActivity(listaranuncio);
    }

    public void listarAnuncio (View view){
        Intent listaranuncio = new Intent(this, ListarAnunciosActivity.class);
        startActivity(listaranuncio);
    }

}






    //public void listarAnuncio(View view){
      //Intent listaranuncio = new Intent(this, ListarAnunciosActivity.class);
        //startActivity(listaranuncio);
     //}




