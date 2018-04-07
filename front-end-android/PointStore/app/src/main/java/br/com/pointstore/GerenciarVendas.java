package br.com.pointstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pointstore.Adapter.ListaDeVendasAdapter;
import br.com.pointstore.Adapter.Menssagem;
import br.com.pointstore.Adapter.Vendas3;
import br.com.pointstore.model.Vendas;
import rest.VendasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GerenciarVendas extends AppCompatActivity {
    private ListView listViewVendas;
    private VendasService mVendasService;
    private String idUsuario;
    private Vendas3 vendasSelecionado;

    public void restartActivity(){
        Intent mIntent = getIntent();
        finish(); startActivity(mIntent); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_vendas);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.3.2").
                addConverterFactory(JacksonConverterFactory.create()).build();
        mVendasService = retrofit.create(VendasService.class);
        Bundle bundle = getIntent().getExtras();

        /*Recebendo por intent o ide de usuário  para fazer busca*/
        idUsuario = (String) bundle.getString("id_usuario");

        listViewVendas = (ListView) findViewById(R.id.listViewVendas);

        Call<List<Vendas3>> listarTodasVendas = mVendasService.listarTodasAsVendasDoUsuarioPorId(idUsuario);

        listarTodasVendas.enqueue(new Callback<List<Vendas3>>() {
            @Override
            public void onResponse(Call<List<Vendas3>> call, Response<List<Vendas3>> response) {
                ArrayList<Vendas3> arrayList = new ArrayList<>();
                arrayList = (ArrayList<Vendas3>) response.body();

                final ListaDeVendasAdapter adaptadorListaResponse =    new ListaDeVendasAdapter(GerenciarVendas.this,
                        R.layout.adapter_view_listar_meus_anuncios, arrayList);

                listViewVendas.setAdapter(adaptadorListaResponse);

                listViewVendas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        vendasSelecionado = adaptadorListaResponse.getItem(position);
                        AlertDialog.Builder adb = new AlertDialog.Builder(GerenciarVendas.this);
                        adb.setTitle("ATENÇÃO !");
                        adb.setMessage("Deseja realmente excluir este anuncio?!");

                        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                String venda_id = vendasSelecionado.getVenda_id();
                                Call<Menssagem> excluirVenda = mVendasService.excluirVenda(venda_id);

                                excluirVenda.enqueue(new Callback<Menssagem>() {
                                    @Override
                                    public void onResponse(Call<Menssagem> call, Response<Menssagem> response) {
                                        AlertDialog.Builder ok = new AlertDialog.Builder(GerenciarVendas.this);
                                        ok.setTitle("Sucesso !");
                                        Menssagem menssagem = response.body();
                                        ok.setMessage(menssagem.getMensagem());
                                        //ok.setMessage(""+response.body());
                                        ok.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                restartActivity();
                                                //this.finish();
                                            }
                                        });

                                        ok.show();

/*
                                        Context context = getApplicationContext();


                                        Toast toast = Toast.makeText(context,"Menssagem"+menssagem.getMensagem() , Toast.LENGTH_SHORT);
                                        toast.show();*/
                                    }

                                    private void finish() {
                                    }

                                    @Override
                                    public void onFailure(Call<Menssagem> call, Throwable t) {
                                        AlertDialog.Builder adb = new AlertDialog.Builder(GerenciarVendas.this);
                                        adb.setTitle("Erro !");
                                        adb.setMessage("Não foi possível excluir o anuncio!");

                                    }
                                });



                            }
                        });
                        adb.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                            }
                        });
                        adb.show();



                        return false;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Vendas3>> call, Throwable t) {

                Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context,"Deu erro" , Toast.LENGTH_SHORT);
                toast.show();

            }
        });



    }
}
