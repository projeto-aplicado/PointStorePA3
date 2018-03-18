package br.com.pointstore.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Arley on 12/12/2017.
 * esse objeto é para lista de pontos generica.
 */

public class Pontos {

    @JsonProperty("id")
    private String id;

    @JsonProperty("tipoDePonto")
    private String tipoDePonto;

    @JsonProperty("quantidadePonto")
    private String quantidadePonto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pontos(){}

    public Pontos(Parcel in){ readFromParcelable(in);}

    private void readFromParcelable (Parcel in){
        tipoDePonto = in.readString();
        quantidadePonto = in.readString();
    }

    private void writeToParcel (Parcel out, int flags){
        out.writeString(tipoDePonto);
        out.writeString(quantidadePonto);
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
        return "Ponto: " + this.tipoDePonto;
    }

    public int describeContents(){
        //TODO Não é necessário implementar agora.
        return 0;
    }

    public String gettipoDePonto() {
        return tipoDePonto;
    }

    public void settipoDePonto(String tipoDePonto) {
        this.tipoDePonto = tipoDePonto;
    }

    public String getquantidadePonto() {
        return quantidadePonto;
    }

    public void setquantidadePonto(String quantidadePonto) {
        this.quantidadePonto = quantidadePonto;
    }
}
