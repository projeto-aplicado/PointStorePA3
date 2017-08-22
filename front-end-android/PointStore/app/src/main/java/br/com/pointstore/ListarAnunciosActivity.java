package br.com.pointstore;

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

import br.com.pointstore.model.Usuario;
import br.com.pointstore.util.CadastrarPontos;
import br.com.pointstore.util.CadastrarVendas;
import br.com.pointstore.util.CadastroTenhoInteresse;
import br.com.pointstore.util.FinalizarCompra;
import br.com.pointstore.util.ListarPontos;
import br.com.pointstore.util.Login;
import br.com.pointstore.util.Perfil;

public class ListarAnunciosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario user;
    private TextView userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_anuncios_layout);
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

        //this.userLogin = (TextView) findViewById(R.id.textViewLogin);
        //this.userLogin.setText(user.getLogin());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        user = (Usuario) getIntent().getSerializableExtra("user");

        if (id == R.id.nav_meuperfil) {
            Intent meuPerfil = new Intent(this, Perfil.class);
            meuPerfil.putExtra("user", user);
            startActivity(meuPerfil);
        } else if (id == R.id.nav_meuspontos) {
            Intent listarPontos = new Intent(this, ListarPontos.class);
            startActivity(listarPontos);
        } else if (id == R.id.nav_tenhointeresse) {
            Intent cadastroTenhoInteresse = new Intent(this, CadastroTenhoInteresse.class);
            startActivity(cadastroTenhoInteresse);
        }else if (id == R.id.nav_cadastrarvendas) {
            Intent cadastrarVendas = new Intent(this, CadastrarVendas.class);
            startActivity(cadastrarVendas);
        } else if (id == R.id.nav_finalizarcompra) {
            Intent finalizarCompra = new Intent(this, FinalizarCompra.class);
            startActivity(finalizarCompra);
        } else if (id == R.id.nav_cadastrarpontos) {
            Intent cadastrarPontos = new Intent(this, CadastrarPontos.class);
            startActivity(cadastrarPontos);
        } else if (id == R.id.nav_sair) {
            Intent sair = new Intent(this, Login.class);
            startActivity(sair);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void finalizarCompra (View view) {
        Intent finalizarcompra = new Intent(this, FinalizarCompra.class);
        startActivity(finalizarcompra);
    }

}
