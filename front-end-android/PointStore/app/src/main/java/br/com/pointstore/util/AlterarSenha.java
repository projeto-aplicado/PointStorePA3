package br.com.pointstore.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.pointstore.R;
import br.com.pointstore.model.Usuario;
import rest.LoginService;
import rest.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AlterarSenha extends AppCompatActivity {

    private EditText editTextEmailAtual;
    private EditText editTextNovaSenha;
    private EditText editTextConfirmarNovaSenha;
    private Usuario usuario;
    private LoginService mLoginAlterarSenhaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.17:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mLoginAlterarSenhaService = retrofit.create(LoginService.class);


        editTextEmailAtual = (EditText) findViewById(R.id.editTextAlterEmailAtual);
        editTextNovaSenha = (EditText) findViewById(R.id.editTextAlterNovaSenha);
        editTextConfirmarNovaSenha = (EditText) findViewById(R.id.editTextAlterConfirmarSenha);

    }

    public void atualizarSenha (View v) {

        usuario = new Usuario();

        usuario.setEmail(editTextEmailAtual.getText().toString());
        usuario.setSenha(editTextNovaSenha.getText().toString());

        if ((editTextEmailAtual.getText().length() > 0) && (editTextNovaSenha.getText().length() > 0) && (editTextConfirmarNovaSenha.getText().length() > 0) &&
                ((editTextNovaSenha.getText().toString()).equals(editTextConfirmarNovaSenha.getText().toString())) ) {

            Call<Usuario> userCallAtualizaSenha = mLoginAlterarSenhaService.atualizaSenha(usuario, usuario.getEmail().toString());
            userCallAtualizaSenha.enqueue(new Callback<Usuario>() {
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

            if (editTextEmailAtual.getText().length() <= 0){
                editTextEmailAtual.setError("Campo email é obrigatório");
            }

            if (!(editTextNovaSenha.getText().toString()).equals(editTextConfirmarNovaSenha.getText().toString())) {

                    editTextNovaSenha.setError("Senhas não conferem");
                    editTextConfirmarNovaSenha.setError("Senhas não conferem");
            }

        }
    }
}
