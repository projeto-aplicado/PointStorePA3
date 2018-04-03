package br.com.pointstore.util;

/**
 * Created by Juan on 05/05/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;

import br.com.pointstore.Adapter.ListaDeVendasAdapter;
import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.UsuarioLogin;
import br.com.pointstore.Adapter.Vendas2;
import br.com.pointstore.Adapter.Vendas3;
import br.com.pointstore.ListarAnunciosActivity;
import br.com.pointstore.R;
import br.com.pointstore.model.Compra;
import br.com.pointstore.model.Usuario;
import br.com.pointstore.model.Vendas;
import rest.LoginService;
import rest.VendasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class FinalizarCompra extends Activity {

    private Spinner spn1;
    private List<String> nomes = new ArrayList<String>();
    private String nome;
    private VendasService mVendasService;
    private Vendas3 vendasSelecionado;
    private Usuario usuarioSelecionado;
    private LoginService mLoginService;

    /*Spinner*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        mVendasService  = retrofit.create(VendasService.class);
        //Adicionando Nomes no ArrayList
        nomes.add("Credito");
        nomes.add("Boleto");
        nomes.add("PayPal");


        //Identifica o Spinner no layout
        spn1 = (Spinner) findViewById(R.id.spinner);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                nome = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                Snackbar.make(findViewById(R.id.button2), nome + " Selecionado", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Pegar dados do usuario vendedor*/

        vendasSelecionado = (Vendas3) getIntent().getSerializableExtra("vendas");
        usuarioSelecionado = (Usuario) getIntent().getSerializableExtra("user");

        TextView tituloTextPonto = (TextView) findViewById(R.id.tituloTextPonto);
        TextView quantidade_pontos = (TextView) findViewById(R.id.quantidade_pontos);
        TextView valor_venda = (TextView) findViewById(R.id.valor_venda);
        TextView emailText = (TextView) findViewById(R.id.emailText);//nome do usuario vendedor
        ImageView appImagem = (ImageView) findViewById(R.id.appImagem);
        TextView textViewCredito = (TextView) findViewById(R.id.textViewCredito);
        TextView textView11 = (TextView) findViewById(R.id.textView11); // nome do comprador
        final Button button2 = (Button) findViewById(R.id.button2);
        String tipoImagem = vendasSelecionado.getTitulo();

        tituloTextPonto.setText(vendasSelecionado.getTitulo().toString());
        quantidade_pontos.setText(vendasSelecionado.getQtd_pontos().toString());
        valor_venda.setText(vendasSelecionado.getValor().toString());
        emailText.setText(vendasSelecionado.getLogin().toString());
        textViewCredito.setText(usuarioSelecionado.getCredito().toString());
        textView11.setText(usuarioSelecionado.getLogin().toString());


        vendasSelecionado.setQtd_pontos(quantidade_pontos.toString());
        vendasSelecionado.setId_usuario_vendedor(emailText.toString());
        vendasSelecionado.setValor(valor_venda.toString());
        vendasSelecionado.setTitulo(tituloTextPonto.toString());

        if (tipoImagem.equals("tam")|| tipoImagem.equals("TAM")){
            appImagem.setImageResource(R.drawable.multiplus_tam_logo);
        }
        else {
            appImagem.setImageResource(R.drawable.smiles_gol_logo);
        }


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Compra compra = new Compra();

                compra.setId_usuario(usuarioSelecionado.getIdUsuario().toString());
                compra.setVenda_id(vendasSelecionado.getVenda_id());
                final Call<Menssagem> menssagemCall = mVendasService.finalizarcompra(compra);

                menssagemCall.enqueue(new Callback<Menssagem>() {
                    @Override
                    public void onResponse(Call<Menssagem> call, Response<Menssagem> response) {
                        Menssagem menssagem = new Menssagem();
                        menssagem = response.body();
                        Context context = getApplicationContext();
                        /*
                        Toast toast = Toast.makeText(context, " : "+menssagem.getMensagem(), Toast.LENGTH_SHORT);
                        toast.show();
                        */
                        Intent paginainicial = new Intent(FinalizarCompra.this, ListarAnunciosActivity.class);
                        startActivity(paginainicial);
                        this.finish();
                    }

                    private void finish() {
                    }

                    @Override
                    public void onFailure(Call<Menssagem> call, Throwable t) {

                    }
                });


                
            }
        });


    }

    }







