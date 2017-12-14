package br.com.pointstore;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pointstore.model.Pontos;

public class CadastrarSeusPontos extends AppCompatActivity {

    private ListView listViewPontos;

    private ArrayList<Pontos> listaDePontos = new ArrayList<Pontos>();
    private ArrayAdapter<Pontos> adaptadorLista;

    private  Pontos pontosSelecionado= new Pontos();
    private int adaptadorLayout = android.R.layout.simple_list_item_1;

    private TextView NomeSelecionadotextView;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pontos2);

        listViewPontos =  (ListView) findViewById(R.id.listViewDePontos);

        NomeSelecionadotextView = (TextView) findViewById(R.id.pontoSelecionado);

        button = (Button)findViewById(R.id.buttonGravarDados);

        final Pontos pontos = new Pontos();
        pontos.setNome("Prêmia"); pontos.setQuantidade("");

        final Pontos pontos1 = new Pontos();
        pontos1.setNome("Avianca"); pontos1.setQuantidade("");

        final Pontos pontos2 = new Pontos();
        pontos2.setNome("Milhas da Gol"); pontos2.setQuantidade("");

        final Pontos pontos3 = new Pontos();
        pontos3.setNome("Varig"); pontos3.setQuantidade("");

        final Pontos pontos4 = new Pontos();
        pontos4.setNome("Tudo Azul"); pontos4.setQuantidade("");

        final Pontos pontos5 = new Pontos();
        pontos5.setNome("Não seio o que implementar"); pontos5.setQuantidade("");

        listaDePontos .add(pontos);listaDePontos .add(pontos1);listaDePontos .add(pontos2);listaDePontos .add(pontos3);listaDePontos .add(pontos4);listaDePontos .add(pontos5);

        adaptadorLista = new ArrayAdapter<Pontos>(this, adaptadorLayout, listaDePontos);

        listViewPontos.setAdapter(adaptadorLista);

        pontosSelecionado.setNome("");
        listViewPontos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pontosSelecionado = adaptadorLista.getItem(i);

               // Toast.makeText(getApplication(), "Ponto selecionado : "
                       // + pontosSelecionado.getNome(), Toast.LENGTH_LONG).show();

                Snackbar.make(findViewById(R.id.buttonGravarDados), pontosSelecionado.getNome(), Snackbar.LENGTH_SHORT).show();

                NomeSelecionadotextView.setText(pontosSelecionado.getNome());
            }




        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CadastrarSeusPontos.this,ListarAnunciosActivity.class);

                startActivity(intent);

            }
        });
    }
}
