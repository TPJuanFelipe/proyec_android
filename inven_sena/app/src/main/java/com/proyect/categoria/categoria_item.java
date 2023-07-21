package com.proyect.categoria;

import android.os.Parcel;
import android.os.Parcelable;

public class categoria_item implements Parcelable {

    int id_categoria;
    String categorias;
    int codigo_admin;

    public categoria_item(){}

    protected categoria_item ( Parcel in ) {
        id_categoria = in.readInt ();
        categorias = in.readString ();
        codigo_admin = in.readInt ();
    }

    public int getId_categoria () {
        return id_categoria;
    }

    public String getCategorias () {
        return categorias;
    }

    public int getCodigo_admin () {
        return codigo_admin;
    }

    public void setId_categoria ( int id_categoria ) {
        this.id_categoria = id_categoria;
    }

    public void setCategorias ( String categorias ) {
        this.categorias = categorias;
    }

    public void setCodigo_admin ( int codigo_admin ) {
        this.codigo_admin = codigo_admin;
    }

    @Override
    public String toString () {
        return "categoria_item{" +
                "id_categoria=" + id_categoria +
                ", categorias='" + categorias + '\'' +
                ", codigo_admin=" + codigo_admin +
                '}';
    }
    @Override
    public void writeToParcel ( Parcel dest, int flags ) {
        dest.writeInt ( id_categoria );
        dest.writeString ( categorias );
        dest.writeInt ( codigo_admin );
    }

    @Override
    public int describeContents () {
        return 0;
    }

    public static final Creator<categoria_item> CREATOR = new Creator<categoria_item> () {
        @Override
        public categoria_item createFromParcel ( Parcel in ) {
            return new categoria_item ( in );
        }

        @Override
        public categoria_item[] newArray ( int size ) {
            return new categoria_item[size];
        }
    };



}
