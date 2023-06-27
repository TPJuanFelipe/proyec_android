package com.proyect.login_y_registro;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class login implements Parcelable {
    private int identifi;
    private String tipo_identifi;
    private String nombre_Usu;
    private String correo_Usu;
    private String usuario;
    private String contrasena;
    public login () {

    }

    protected login ( Parcel in ) {
        identifi = in.readInt ();
        tipo_identifi = in.readString ();
        nombre_Usu = in.readString ();
        correo_Usu = in.readString ();
        usuario = in.readString ();
        contrasena = in.readString ();
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




    public int getIdentifi () {
        return identifi;
    }

    public String getTipo_identifi () {
        return tipo_identifi;
    }

    public String getNombre_Usu () {
        return nombre_Usu;
    }

    public String getCorreo_Usu () {
        return correo_Usu;
    }

    public String getUsuario () {
        return usuario;
    }

    public String getContrasena () {
        return contrasena;
    }

    public void setIdentifi ( int identifi ) {
        this.identifi = identifi;
    }

    public void setTipo_identifi ( String tipo_identifi ) {
        this.tipo_identifi = tipo_identifi;
    }

    public void setNombre_Usu ( String nombre_Usu ) {
        this.nombre_Usu = nombre_Usu;
    }

    public void setCorreo_Usu ( String correo_Usu ) {
        this.correo_Usu = correo_Usu;
    }

    public void setUsuario ( String usuario ) {
        this.usuario = usuario;
    }

    public void setContrasena ( String contrasena ) {
        this.contrasena = contrasena;
    }

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( @NonNull Parcel parcel, int i ) {
        parcel.writeInt ( identifi );
        parcel.writeString ( tipo_identifi );
        parcel.writeString ( nombre_Usu );
        parcel.writeString ( correo_Usu );
        parcel.writeString ( usuario );
        parcel.writeString ( contrasena );
    }
}
