package br.com.pointstore.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.pointstore.R;
import br.com.pointstore.model.Usuario;
import rest.LoginService;
import rest.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Perfil extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNome;
    private EditText editTextSobrenome;
    private EditText editTextEmail;
    private EditText editTextCPF;
    private EditText editTextLogin;
    private EditText editTextSenha;
    private Button btnAtualizar;
    private UsuarioService mUsuarioService;
    private Usuario usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.17:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.mUsuarioService = retrofit.create(UsuarioService.class);

        this.editTextNome = (EditText) findViewById(R.id.editTextNome);
        this.editTextSobrenome = (EditText) findViewById(R.id.editTextSobrenome);
        this.editTextEmail = (EditText) findViewById(R.id.editTextAttEmail);
        this.editTextCPF = (EditText) findViewById(R.id.editTextAttCPF);
        this.editTextLogin = (EditText) findViewById(R.id.editTextAttUsuario);
        this.editTextSenha = (EditText) findViewById(R.id.editTextAttSenha);

        this.btnAtualizar = (Button) findViewById(R.id.buttonAtualizar);
        this.btnAtualizar.setOnClickListener(this);

        usuario = (Usuario) getIntent().getSerializableExtra("user");

        this.editTextNome.setText(usuario.getNome());
        this.editTextSobrenome.setText(usuario.getSobrenome());
        this.editTextEmail.setText(usuario.getEmail());
        this.editTextLogin.setText(usuario.getLogin());
        this.editTextSenha.setText(usuario.getSenha());
        this.editTextCPF.setText(usuario.getCpf());


    }


    @Override
    public void onClick(View v) {

        usuario = new Usuario(usuario.getIdUsuario(), this.editTextNome.getText().toString(), this.editTextSobrenome.getText().toString(), null, this.editTextEmail.getText().toString(), this.editTextCPF.getText().toString(), this.editTextLogin.getText().toString(), this.editTextSenha.getText().toString(), null, null, null, null);

        if((this.editTextNome.getText().length() > 0) && (this.editTextSobrenome.getText().length() > 0) &&

                (this.editTextEmail.getText().length() > 0)&& (this.editTextCPF.getText().length() > 0)&&

                (this.editTextLogin.getText().length() > 0) && (this.editTextSenha.getText().length() > 0) &&

                (this.editTextCPF.getText().length() > 0)){

            Call<Usuario> userCall = mUsuarioService.updateUser(this.usuario);
            userCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e("APP", t.getMessage());
                }
            });

        }else{
            if (editTextNome.getText().length() <= 0){
                editTextNome.setError("Campo nome é obrigatório");
            }
            if (editTextSobrenome.getText().length() <= 0){
                editTextSobrenome.setError("Campo sobrenome é obrigatório");
            }
            if (editTextEmail.getText().length() <= 0){
                editTextEmail.setError("Campo email é obrigatório");
            }
            if (editTextCPF.getText().length() <= 0){
                editTextCPF.setError("Campo cpf é obrigatório");
            }
            if (editTextLogin.getText().length() <= 0){
                editTextLogin.setError("Campo login é obrigatório");
            }
            if (editTextSenha.getText().length() <= 0){
                editTextSenha.setError("Campo senha é obrigatório");
            }
            if (editTextCPF.getText().length() <= 0){
                editTextCPF.setError("Campo cpf é obrigatório");
            }

        }
    }
}
