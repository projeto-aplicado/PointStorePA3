package br.com.pointstore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.pointstore.R;


/**
 * Created by Arley on 26/03/2018.
 */

public class ListaDeVendasAdapter extends ArrayAdapter<Vendas3> {

    private static final String TAG ="ListaDeVendas";
    private Context mContext;
    int mResource;


    public ListaDeVendasAdapter(Context context, int resource, ArrayList<Vendas3> lista_de_vendas) {
        super(context, resource, lista_de_vendas);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Pegando informações do objeto Vendas2

          String id = getItem(position).getId();

          String id_usuario_vendedor = getItem(position).getId_usuario_vendedor();

          String id_usuario_comprador = getItem(position).getId_usuario_comprador();

          String id_meus_pontos = getItem(position).getId_meus_pontos();

          String titulo = getItem(position).getTitulo();

          String valor = getItem(position).getValor();

          String status =  getItem(position).getStatus();

          String qtd_pontos = getItem(position).getQtd_pontos();

          String nome = getItem(position).getNome();

          String sobrenome = getItem(position).getSobrenome();

          String cpf = getItem(position).getCpf();

          String credito = getItem(position).getCredito();

          String email = getItem(position).getEmail();

          String login = getItem(position).getLogin();

          String senha = getItem(position).getSenha();

          String venda_id = getItem(position).getVenda_id();



        //criando o objeto do tipo venda com as informações



        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tituloTextPonto = (TextView) convertView.findViewById(R.id.tituloTextPonto);
        TextView quantidade_pontos = (TextView) convertView.findViewById(R.id.quantidade_pontos);
        TextView valor_venda = (TextView) convertView.findViewById(R.id.valor_venda);
        TextView vendedor = (TextView) convertView.findViewById(R.id.emailText);

        ImageView imagem = (ImageView) convertView.findViewById(R.id.appImagem);


        if (titulo.equals("tam")|| titulo.equals("TAM")){
            imagem.setImageResource(R.drawable.multiplus_tam_logo);
        }
        else {
            imagem.setImageResource(R.drawable.smiles_gol_logo);
        }
        if (titulo.equals("tam")|| titulo.equals("TAM")){
            tituloTextPonto.setText("Tam");

        }
        else {
            tituloTextPonto.setText("Gol");
        }



        vendedor.setText(login);
        //tituloTextPonto.setText(titulo);
        quantidade_pontos.setText(qtd_pontos);
        valor_venda.setText(valor);

        return convertView;


    }
}
