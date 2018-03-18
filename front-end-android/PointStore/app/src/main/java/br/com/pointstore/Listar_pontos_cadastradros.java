package br.com.pointstore;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.pointstore.model.Pontos;
import br.com.pointstore.model.Usuario;

public class Listar_pontos_cadastradros extends AppCompatActivity {

    private ListView listViewPontosCadastrados;

    private ArrayList<Pontos> listaDePontos = new ArrayList<Pontos>();
    //private ArrayList<Pontos> listaDePontos = new ArrayList<Pontos>();
    private ArrayAdapter<Pontos> adaptadorLista;

    private  Pontos pontosSelecionado= new Pontos();

    private int adaptadorLayout = android.R.layout.simple_list_item_1;

    private TextView NomeSelecionadotextView;

    private Button button;

    private Usuario user;

    private ArrayList<Pontos> listDePontosRecebida = new ArrayList<Pontos>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pontos_cadastradros);


        listViewPontosCadastrados =  (ListView) findViewById(R.id.listViewPontosCadastros);

        NomeSelecionadotextView = (TextView) findViewById(R.id.pontoSelecionado);

        button = (Button)findViewById(R.id.buttonGravarDados);

        //listaDePontos = (ArrayList<Pontos>) getIntent().getSerializableExtra("listaDePontos");

        /*
        final Pontos pontos = new Pontos();
        pontos.setNome("Prêmia"); pontos.setQuantidade("50");

        final Pontos pontos1 = new Pontos();
        pontos1.setNome("Avianca"); pontos1.setQuantidade("25");

        final Pontos pontos2 = new Pontos();
        pontos2.setNome("Milhas da Gol"); pontos2.setQuantidade("60");

        final Pontos pontos3 = new Pontos();
        pontos3.setNome("Varig"); pontos3.setQuantidade("75");

        final Pontos pontos4 = new Pontos();
        pontos4.setNome("Tudo Azul"); pontos4.setQuantidade("55");

        final Pontos pontos5 = new Pontos();
        pontos5.setNome("Latam"); pontos5.setQuantidade("25");*/


        /*
        Toast.makeText(getApplication(), "Ponto : "
                + pontos.getNome(), Toast.LENGTH_LONG).show();*/

       // listaDePontos .add(pontos);listaDePontos .add(pontos1);listaDePontos .add(pontos2);listaDePontos .add(pontos3);listaDePontos .add(pontos4);listaDePontos .add(pontos5);

        adaptadorLista = new ArrayAdapter<Pontos>(this, adaptadorLayout, listaDePontos);

        listViewPontosCadastrados.setAdapter(adaptadorLista);

       // pontosSelecionado.setNome("");
        listViewPontosCadastrados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pontosSelecionado = adaptadorLista.getItem(i);

                //Toast.makeText(getApplication(), "Ponto selecionado : "
                        //+ pontosSelecionado.getNome(), Toast.LENGTH_LONG).show();
                /*
                Snackbar.make(findViewById(R.id.btRetorna), "Ponto selecionado : "
                        + pontosSelecionado.getNome(), Snackbar.LENGTH_SHORT).show();*/
                //NomeSelecionadotextView.setText(pontosSelecionado.getNome());
            }




        });




    }
}
