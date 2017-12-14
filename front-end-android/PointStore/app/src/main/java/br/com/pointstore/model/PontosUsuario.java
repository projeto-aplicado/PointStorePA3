package br.com.pointstore.model;

/**
 * Created by Arley on 13/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Arley on 12/12/2017.
 * esse objeto é para lista de pontos do usuario que vai ser carregada.
 */

public class PontosUsuario implements Serializable {

    private String nome;
    private String quantidade;



    public PontosUsuario(){}

    public PontosUsuario(Parcel in){ readFromParcelable(in);}

    private void readFromParcelable (Parcel in){
        nome = in.readString();
        quantidade = in.readString();
    }

    private void writeToParcel (Parcel out, int flags){
        out.writeString(nome);
        out.writeString(quantidade);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public Pontos createFromParcel(Parcel in){
            return new Pontos(in);
        }

        public Pontos [] newArray(int size){
            return new Pontos[size];
        }
    };

    public String toString(){
        return "Pontos: " + this.nome+" Quantidade :"+this.quantidade ;
    }

    public int describeContents(){
        //TODO Não é necessário implementar agora.
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
