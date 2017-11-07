package br.com.pointstore.util;

import android.app.Activity;
import android.os.Bundle;

import br.com.pointstore.R;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 20/10/2017.
 */

public class ListarCompras extends Activity {

    private String usuario;
    private String tipoDePonto;
    private String valor;
    private String descricao;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoDePonto() {
        return tipoDePonto;
    }

    public void setTipoDePonto(String tipoDePonto) {
        this.tipoDePonto = tipoDePonto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public static void main (String[] args) {
        String[] compras = {"IPIRANGA", "PAODEACUCAR", "DOTS", "TAM", "AZUL"};
        System.out.printf("%s %12s \n", "Index", "Tipos de Pontos");
        for( int counter = 0; counter < compras.length; counter ++){
            System.out.printf("%5d %4s %4d \n", counter, "=>" , compras[ counter ]);
        }

        String [] usuario = {"JOAO", "JOSE", "MARIA", "JAQUELINE", "MOISES"};
        System.out.printf("%s %12s \n", "Index", "Usuarios");
        for (int contador = 0; contador < usuario.length; contador ++){
            System.out.printf("%5d %4s %4d \n", contador, "=>" , usuario[ contador ]);
        }

        double[] valor = {70.00, 60.00, 140.00, 20.00, 100.00};
        System.out.printf("%s %12s \n", "Index", "Valores");
        for (int contador = 0; contador < valor.length; contador ++){
            System.out.printf("%5d %4s %4d \n", contador, "=>" , valor[ contador ]);
        }

    }



}
