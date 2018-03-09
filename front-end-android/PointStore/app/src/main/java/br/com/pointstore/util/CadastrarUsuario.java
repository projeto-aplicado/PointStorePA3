package br.com.pointstore.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.pointstore.Adapter.UsuarioCadastro;
import br.com.pointstore.R;
import br.com.pointstore.model.Usuario;
import okio.Buffer;
import rest.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 11/03/2017.
 *
 * Atualizacao 30/11/2017
 * ->configuracao da conexao com genymotion
 * ->implementacao  da entidade no pacote adapter para cadastrar usuario "UsuarioCadastro"
 * -> cadastro realizado com sucesso
 * ->realizado teste na base de dados url http://localhost/pointstorePA3/index.php/usuario
 *
 */

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
                //.baseUrl("http://10.0.2.2:8080/")
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mUsuarioService = retrofit.create(UsuarioService.class);


        editTextNome = (EditText) findViewById(R.id. editTextNome);
        editTextCadEmail = (EditText) findViewById(R.id.editTextCadEmail);
        editTextSobrenome = (EditText) findViewById(R.id.editTextSobrenome);
        editTextCadSenha = (EditText) findViewById(R.id.editTextCadSenha);
        editTextCadUsuario = (EditText) findViewById (R.id.editTextCadUsuario);
    }

    public boolean validaUsuarioExpressao(UsuarioCadastro usuarioCadastro) {

        String emailParaVerificar = usuarioCadastro.getEmail();

        return emailParaVerificar.matches("[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-z]{2,4}");

        /*
        * Aceita palavras que comecem de a ate z maiúsculo ou minusculo
        * Depois aceita de a ate z e alguns caracteres especiais como . _ e -
        * Aceita um único @
        * Por fim tem que ter de 2 a 4 letras no final da palavra
        */

    }

    public void cadastrarUsuario (View v) {


        UsuarioCadastro usuarioCadastro = new UsuarioCadastro();

        usuarioCadastro.setNome(editTextNome.getText().toString());
        usuarioCadastro.setSobrenome(editTextSobrenome.getText().toString());
        usuarioCadastro.setEmail(editTextCadEmail.getText().toString());
        usuarioCadastro.setLogin(editTextCadUsuario.getText().toString());
        usuarioCadastro.setSenha(editTextCadSenha.getText().toString());

        if ((editTextNome.getText().length() > 0) && (editTextCadEmail.getText().length() > 0) &&

                (editTextSobrenome.getText().length() > 0)&& (editTextCadSenha.getText().length() > 0)&&

                (editTextCadUsuario.getText().length()> 0)){


            /*Aqui irei inserir a expressao regular de validar email, se a expressao estiver correta
            * prossegue o block de instrução*/

            if(validaUsuarioExpressao(usuarioCadastro)){


                Call<Usuario> userCall = mUsuarioService.createUserNoPoints(usuarioCadastro);

                Buffer buffer = new Buffer();
                try {
                    userCall.request().body().writeTo(buffer);
                    System.out.println(buffer.readUtf8Line());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

            }else {

                Context context = getApplicationContext();
                CharSequence text = "Email invalido";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


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
