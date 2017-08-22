package br.com.pointstore.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.pointstore.R;
import rest.UsuarioService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 07/04/2017.
 */

public class ListarPontos extends Activity{

    private String nome;
    private String descricao1;
    private String descricao2;


    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao1;
    }

    public String getDescricao2() {
        return descricao2;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pontos);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }




}
