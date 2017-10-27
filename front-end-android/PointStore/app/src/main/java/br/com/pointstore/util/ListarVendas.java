package br.com.pointstore.util;

import android.app.Activity;
import android.os.Bundle;

import java.util.Date;

import br.com.pointstore.R;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juan on 20/10/2017.
 */

public class ListarVendas extends Activity {

    private String usuarioCliente;
    private String tipoDePonto;
    private String valor;
    private Date data;


    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public static void main(String[] args) {
        String[] clientes = {"LEILA", "DIEGO", "ANNA", "GABRIELA", "FÁBIO"};
        System.out.printf("%s %12s \n", "Index", "Clientes");
        for (int contador = 0; contador < clientes.length; contador++) {
            System.out.printf("%5d %4s %4d \n", contador, "=>", clientes[contador]);
        }

        double[] datas = {15/02/2017, 16/10/2017, 18/03/2017, 10/01/2017, 03/12/2016};
        System.out.printf("%s %12s \n", "Index", "Datas das Vendas");
        for (int contador = 0; contador < datas.length; contador++) {
            System.out.printf("%5d %4s %4d \n", contador, "=>", datas[contador]);
        }

    }


}
