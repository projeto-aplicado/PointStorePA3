package br.com.pointstore.Adapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Arley on 12/12/2017.
 * esse objeto é um adapter de pontos, exibindo pontos e quantidade,diferente de pontos comum que
 * não seta quantidade.
 */

public class PontosMeusPontos {

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

    public PontosMeusPontos(){}

    public PontosMeusPontos(Parcel in){ readFromParcelable(in);}

    private void readFromParcelable (Parcel in){
        tipoDePonto = in.readString();
        quantidadePonto = in.readString();
    }

    private void writeToParcel (Parcel out, int flags){
        out.writeString(tipoDePonto);
        out.writeString(quantidadePonto);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public PontosMeusPontos createFromParcel(Parcel in){
            return new PontosMeusPontos(in);
        }

        public PontosMeusPontos[] newArray(int size){
            return new PontosMeusPontos[size];
        }
    };

    public String toString(){
        return "Ponto: " + this.tipoDePonto+", quantidade : "+this.getquantidadePonto();
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
