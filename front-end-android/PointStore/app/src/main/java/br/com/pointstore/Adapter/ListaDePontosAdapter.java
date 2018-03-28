package br.com.pointstore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.pointstore.R;
import br.com.pointstore.model.Pontos;


/**
 * Created by Arley on 26/03/2018.
 */

public class ListaDePontosAdapter extends ArrayAdapter<Pontos> {

    private static final String TAG ="ListaDePontosAdapter";
    private Context mContext;
    int mResource;

    public ListaDePontosAdapter(Context context, int resource, ArrayList<Pontos> lista_de_pontos) {
        super(context, resource, lista_de_pontos);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Pegando informações do objeto ponto
        String nome = getItem(position).gettipoDePonto();
        String quantidade = getItem(position).getquantidadePonto();
        String id_ponto = getItem(position).getId();

        //criando o objeto do tipo ponto com as informações

        //Pontos pontos = new Pontos(nome,quantidade,valor);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvNome = (TextView) convertView.findViewById(R.id.txv_pontos);
        TextView tvQuantidade = (TextView) convertView.findViewById(R.id.txv_quantidade);



        tvNome.setText(nome);
        tvQuantidade.setText(quantidade);


        return convertView;


    }
}
