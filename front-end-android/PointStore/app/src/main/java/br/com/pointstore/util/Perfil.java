package br.com.pointstore.util;

import android.content.Context;
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
import android.widget.Toast;

/*import java.util.Collection;
import javax.inject.Inject;*/


import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.UsuarioCadastro;
import br.com.pointstore.DAO.DataAccessObject;
import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.R;
import br.com.pointstore.model.MeusPontos;
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
 */

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
    final DataAccessObject dataAccessObject = new DataAccessObject(this);

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
                .baseUrl("http://10.0.3.2")
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
    public boolean validaUsuarioExpressao(Usuario usuario) {

        String emailParaVerificar = usuario.getEmail();

        return emailParaVerificar.matches("[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-z]{2,4}");

        /*
        * Aceita palavras que comecem de a ate z maiúsculo ou minusculo
        * Depois aceita de a ate z e alguns caracteres especiais como . _ e -
        * Aceita um único @
        * Por fim tem que ter de 2 a 4 letras no final da palavra
        */

    }

    @Override
    public void onClick(View v) {

        usuario = new Usuario(usuario.getIdUsuario(),this.editTextNome.getText().toString(), this.editTextSobrenome.getText().toString(), null, this.editTextEmail.getText().toString(), this.editTextCPF.getText().toString(), this.editTextLogin.getText().toString(), this.editTextSenha.getText().toString());


        Context context = getApplicationContext();
        //Toast toast = Toast.makeText(context, " : "+usuario.getIdUsuario(), Toast.LENGTH_SHORT);
        //toast.show();
        Snackbar.make(findViewById(R.id.buttonAtualizar), " : "+usuario.getIdUsuario(), Snackbar.LENGTH_SHORT).show();
        if((this.editTextNome.getText().length() > 0) && (this.editTextSobrenome.getText().length() > 0) &&

                (this.editTextEmail.getText().length() > 0)&& (this.editTextCPF.getText().length() > 0)&&

                (this.editTextLogin.getText().length() > 0) && (this.editTextSenha.getText().length() > 0) &&

                (this.editTextCPF.getText().length() > 0)){

            if(validaUsuarioExpressao(usuario)){
                Call<Menssagem> userCall = mUsuarioService.updateUser(this.usuario);
                userCall.enqueue(new Callback<Menssagem>() {
                    @Override
                    public void onResponse(Call<Menssagem> call, Response<Menssagem> response) {

                        Menssagem menssagem = response.body();

                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, " : "+menssagem.getMensagem(), Toast.LENGTH_SHORT);
                        toast.show();
                        Intent listarAnuncios = new Intent(Perfil.this, ListarAnunciosActivity.class);


                        /*Aqui vai ser chamado a função para atualizar a senha do usuario logado*/
                        dataAccessObject.atualizarSenhaDoUsuarioLogado(usuario);

                        startActivity(listarAnuncios);
                    }

                    @Override
                    public void onFailure(Call<Menssagem> call, Throwable t) {
                        Log.e("APP", t.getMessage());
                        t.printStackTrace();
                    }
                });


            }else{

                Toast toast = Toast.makeText(context, " email do tipo inválido", Toast.LENGTH_SHORT);
                toast.show();
            }



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
