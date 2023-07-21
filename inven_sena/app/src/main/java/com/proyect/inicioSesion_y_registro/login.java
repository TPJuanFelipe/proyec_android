package com.proyect.inicioSesion_y_registro;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class login implements Parcelable {
    private int id_identi_tbj;
    private String tipo_docu_tbj;
    private String nombre_tbj;
    private String correo_tbj;
    private String usu_tbj;
    private String contra_tbj;

    public login(){}

    @Override
    public String toString () {
        return "login{" +
                "tipo_docu_tbj='" + tipo_docu_tbj + '\'' +
                '}';
    }

    protected login ( Parcel in ) {
        id_identi_tbj = in.readInt ();
        tipo_docu_tbj = in.readString ();
        nombre_tbj = in.readString ();
        correo_tbj = in.readString ();
        usu_tbj = in.readString ();
        contra_tbj = in.readString ();
    }

    public static final Creator<login> CREATOR = new Creator<login> () {
        @Override
        public login createFromParcel ( Parcel in ) {
            return new login ( in );
        }

        @Override
        public login[] newArray ( int size ) {
            return new login[size];
        }
    };

    public int getId_identi_tbj () {
        return id_identi_tbj;
    }

    public String getTipo_docu_tbj () {
        return tipo_docu_tbj;
    }

    public String getNombre_tbj () {
        return nombre_tbj;
    }

    public String getCorreo_tbj () {
        return correo_tbj;
    }

    public String getUsu_tbj () {
        return usu_tbj;
    }

    public String getContra_tbj () {
        return contra_tbj;
    }

    public void setId_identi_tbj ( int id_identi_tbj ) {
        this.id_identi_tbj = id_identi_tbj;
    }

    public void setTipo_docu_tbj ( String tipo_docu_tbj ) {
        this.tipo_docu_tbj = tipo_docu_tbj;
    }

    public void setNombre_tbj ( String nombre_tbj ) {
        this.nombre_tbj = nombre_tbj;
    }

    public void setCorreo_tbj ( String correo_tbj ) {
        this.correo_tbj = correo_tbj;
    }

    public void setUsu_tbj ( String usu_tbj ) {
        this.usu_tbj = usu_tbj;
    }

    public void setContra_tbj ( String contra_tbj ) {
        this.contra_tbj = contra_tbj;
    }

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( @NonNull Parcel parcel, int i ) {
        parcel.writeInt ( id_identi_tbj );
        parcel.writeString ( tipo_docu_tbj );
        parcel.writeString ( nombre_tbj );
        parcel.writeString ( correo_tbj );
        parcel.writeString ( usu_tbj );
        parcel.writeString ( contra_tbj );
    }
}
