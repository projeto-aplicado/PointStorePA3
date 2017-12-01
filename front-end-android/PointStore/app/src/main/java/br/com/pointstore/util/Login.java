package br.com.pointstore.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.pointstore.Adapter.UsuarioLogin;
import br.com.pointstore.ListarAnunciosActivity;
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
 * Created by Juan on 11/03/2017.
 *
 * ->Alteração 29/11/2017 Arley
 * ->Auteração na conexão do app com genymotion
 * ->Método Login feito por Arley, se logar e dados estiver inválidos, será retornado  um aviso de dados
 * invalidos
 *
 */

public class Login extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextSenha;
    private LoginService mLoginService;

    private List<Usuario> listaUsuarios;
    private Usuario usuario;

    private String TAG_I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Configuracao para uso da genymtion como emulador android*/
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.3.2").addConverterFactory(JacksonConverterFactory.create()).build();

        mLoginService = retrofit.create(LoginService.class);

        listaUsuarios = new ArrayList<>();

        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);


    }


    public void logar(View v) {

        UsuarioLogin usuarioLogin = new UsuarioLogin();
        usuarioLogin.setLogin(editTextLogin.getText().toString());
        usuarioLogin.setSenha(editTextSenha.getText().toString());


        if ((editTextLogin.getText().length() > 0) && ((editTextSenha.getText().length() > 0))) {


                //Call<Usuario> userLoginCall = mLoginService.loginUser(usuarioLogin);
                Call<Usuario> userLoginCall = mLoginService.loginUser(usuarioLogin);
                userLoginCall.enqueue(new Callback<Usuario>() {



                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        Usuario user = response.body();

                        if(user != null ){

                            Intent listarAnuncios = new Intent(Login.this, ListarAnunciosActivity.class);
                            listarAnuncios.putExtra("user", user);

                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "Bem vindo (a)"+user.getNome(), Toast.LENGTH_SHORT);
                            toast.show();
                            startActivity(listarAnuncios);




                        }else {

                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "Dados inválidos", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                        Log.e("APP", t.getMessage());
                        t.printStackTrace();
                    }
                });

        }else {

            if ((editTextLogin.getText().length() <= 0)) {
                editTextLogin.setError("Login Vazio!");

            }  if ((editTextSenha.getText().length() <= 0)) {
                editTextSenha.setError("Digite a Senha!");
            }

        }
    }
    public void cadastrarUsuario (View v) {
        Intent cadastrarUsuario = new Intent(this, CadastrarUsuario.class);
        startActivity(cadastrarUsuario);
    }
    public void alterarSenha (View v) {
        Intent alterarSenha = new Intent(this, AlterarSenha.class);
        startActivity(alterarSenha);
    }
}