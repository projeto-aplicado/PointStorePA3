package br.com.pointstore.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Arley on 12/12/2017.
 * esse objeto é para lista de pontos generica.
 */

public class Pontos {

    private String nome;
    private String quantidade;



    public Pontos(){}

    public Pontos(Parcel in){ readFromParcelable(in);}

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
        return "Ponto: " + this.nome;
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
