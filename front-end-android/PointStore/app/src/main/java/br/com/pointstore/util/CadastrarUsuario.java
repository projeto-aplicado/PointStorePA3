package br.com.pointstore.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.pointstore.R;
import br.com.pointstore.model.Usuario;
import rest.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CadastrarUsuario extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextCadEmail;
    private EditText editTextSobrenome;
    private EditText editTextCadSenha;
    private EditText editTextCadUsuario;

    private UsuarioService mUsuarioService;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mUsuarioService = retrofit.create(UsuarioService.class);


        editTextNome = (EditText) findViewById(R.id. editTextNome);
        editTextCadEmail = (EditText) findViewById(R.id.editTextCadEmail);
        editTextSobrenome = (EditText) findViewById(R.id.editTextSobrenome);
        editTextCadSenha = (EditText) findViewById(R.id.editTextCadSenha);
        editTextCadUsuario = (EditText) findViewById (R.id.editTextCadUsuario);
    }


    public void cadastrarUsuario (View v) {

        usuario = new Usuario(editTextNome.getText().toString(),editTextSobrenome.getText().toString(),"3",editTextCadEmail.getText().toString(),"",editTextCadUsuario.getText().toString(),editTextCadSenha.getText().toString());


        if ((editTextNome.getText().length() > 0) && (editTextCadEmail.getText().length() > 0) &&

                (editTextSobrenome.getText().length() > 0)&& (editTextCadSenha.getText().length() > 0)&&

                (editTextCadUsuario.getText().length()> 0)){


            Call<Usuario> userCall = mUsuarioService.createUser(usuario);
            userCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {


                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {


                }
            });

            Intent telaDeLogin = new Intent(this, Login.class);
            startActivity(telaDeLogin);

        } else {

            if (editTextNome.getText().length() <= 0){
                editTextNome.setError("Campo nome é obrigatório");
            }
            if (editTextSobrenome.getText().length() <= 0){
                editTextSobrenome.setError("Campo sobrenome é obrigatório");
            }
            if (editTextCadEmail.getText().length() <= 0){
                editTextCadEmail.setError("Campo email é obrigatório");
            }
            if (editTextCadUsuario.getText().length() <= 0){
                editTextCadUsuario.setError("Campo usário é obrigatório");
            }
            if (editTextCadSenha.getText().length() <= 0){
                editTextCadSenha.setError("Campo senha é obrigatório");
            }

        }
    }
}
