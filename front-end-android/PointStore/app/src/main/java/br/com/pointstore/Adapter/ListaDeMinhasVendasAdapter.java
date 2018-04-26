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
import java.util.List;

import br.com.pointstore.R;
import br.com.pointstore.model.Usuario;
import rest.UsuarioService;
import rest.VendasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * Created by Arley on 26/03/2018.
 */

public class ListaDeMinhasVendasAdapter extends ArrayAdapter<Vendas3> {

    private static final String TAG ="ListaDeVendas";
    private Context mContext;
    int mResource;
    private UsuarioService mUsuarioService;


    public ListaDeMinhasVendasAdapter(Context context, int resource, ArrayList<Vendas3> lista_de_vendas) {
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

        TextView tituloTextPonto = (TextView) convertView.findViewById(R.id.tipo_de_ponto_venda);
        TextView quantidade_pontos = (TextView) convertView.findViewById(R.id.quantidade_de_pontos_da_venda_venda);
        TextView valor_venda = (TextView) convertView.findViewById(R.id.preco_da_compra_realizada_venda);

        tituloTextPonto.setText(titulo);
        quantidade_pontos.setText(qtd_pontos);
        valor_venda.setText(valor);

        return convertView;


    }



}
