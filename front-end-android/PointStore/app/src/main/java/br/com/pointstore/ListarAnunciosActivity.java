package br.com.pointstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.pointstore.Adapter.UsuarioLogin;
import br.com.pointstore.DAO.DataAccessObject;
import br.com.pointstore.model.MeusPontos;
import br.com.pointstore.model.Usuario;
import br.com.pointstore.util.CadastrarPontos;
import br.com.pointstore.util.CadastrarVendas;
import br.com.pointstore.util.FinalizarCompra;
import br.com.pointstore.util.ListarPontos;
import br.com.pointstore.util.Login;
import br.com.pointstore.util.Perfil;
import rest.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ListarAnunciosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario user;
    private TextView userLogin;
    UsuarioLogin usuarioLogin = new UsuarioLogin();
    private LoginService mLoginService;
    final DataAccessObject dataAccessObject = new DataAccessObject(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_anuncios_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.3.2").
                addConverterFactory(JacksonConverterFactory.create()).build();

        mLoginService = retrofit.create(LoginService.class);



        /*AREA DE NAVEGAÇÃO*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /*Area de navegação*/

        /*Carregando usuariologin do banco sqlite*/

        usuarioLogin = dataAccessObject.procurarLoginSalvoSQLlite();

        Call<Usuario> userLoginCall = mLoginService.loginUser(usuarioLogin);
        /*Aqui recebe via intenter um objeto do tipo usuario*/

        userLoginCall.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                user = response.body();


                Context context = getApplicationContext();


            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listar_anuncios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*Aqui vai ser carregado o login que foi salvo na atividade de login*/
        usuarioLogin = dataAccessObject.procurarLoginSalvoSQLlite();

        int id = item.getItemId();
        //user = (Usuario) getIntent().getSerializableExtra("user");

        Call<Usuario> userLoginCall = mLoginService.loginUser(usuarioLogin);


        userLoginCall.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                user = response.body();

                Context context = getApplicationContext();

                /*
                Toast toast = Toast.makeText(context, " Nome : " +user.getNome()+" sobrenome : "+user.getSobrenome()
                        +" email : " +user.getEmail()+" login : "+user.getLogin() +" senha : "+user.getSenha(), Toast.LENGTH_SHORT);
                toast.show();*/
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });


        /*-----------------------------------------*/
        if (id == R.id.nav_meuperfil) {
            Intent meuPerfil = new Intent(this, Perfil.class);
            meuPerfil.putExtra("user", user);

            startActivity(meuPerfil);


        } else if (id == R.id.nav_meuspontos) {
            //Intent listarPontos = new Intent(this, ListarPontos.class);
            Intent listarPontos = new Intent(this, Listar_pontos_cadastradros.class);
            startActivity(listarPontos);


        } else if (id == R.id.nav_sair) {
            Intent sair = new Intent(this, Login.class);
            dataAccessObject.deletar();
            this.finish();
            startActivity(sair);

        }else if (id == R.id.nav_home){
            Intent home = new Intent(this, ListarAnunciosActivity.class);
            startActivity(home);


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void finalizarCompra (View view) {
        Intent finalizarcompra = new Intent(this, FinalizarCompra.class);
        startActivity(finalizarcompra);
    }

    public void cadastrarPontos(View view) {
        Intent cadastrarPontos = new Intent (this, CadastrarSeusPontos.class);
        startActivity(cadastrarPontos);
    }

    public void listarPontos(View view) {
        Intent listarpontos = new Intent (this, Listar_pontos_cadastradros.class);
        startActivity(listarpontos);
    }

    public void cadastrarVendas (View view) {
        Intent cadastrarvendas = new Intent(this, CadastrarVendas.class);
        startActivity(cadastrarvendas);
    }
}
