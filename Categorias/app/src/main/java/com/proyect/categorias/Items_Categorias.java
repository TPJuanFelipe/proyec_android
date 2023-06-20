package com.proyect.categorias;

import android.content.ClipData;
import android.os.Parcel;
import android.os.Parcelable;

public class Items_Categorias implements Parcelable {
    private int id_categoria;
    private String categorias;
    private int identifi_admin;

    public Items_Categorias() {
        // Constructor vacío
    }

    protected Items_Categorias(Parcel in) {
        id_categoria = in.readInt ();
        categorias = in.readString ();
        identifi_admin = in.readInt ();
    }

    public static final Creator<Items_Categorias> CREATOR = new Creator<Items_Categorias> () {
        @Override
        public Items_Categorias createFromParcel( Parcel in) {
            return new Items_Categorias (in);
        }

        @Override
        public Items_Categorias[] newArray( int size) {
            return new Items_Categorias[size];

        }
    };
    public int getId_categoria () {
        return id_categoria;
    }

    public String getCategorias () {
        return categorias;
    }

    public int getIdentifi_admin () {
        return identifi_admin;
    }

    public void setId_categoria ( int id_categoria ) {
        this.id_categoria = id_categoria;
    }

    public void setCategorias ( String categorias ) {
        this.categorias = categorias;
    }

    public void setIdentifi_admin ( int identifi_admin ) {
        this.identifi_admin = identifi_admin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_categoria);
        dest.writeString ( categorias );
        dest.writeInt ( identifi_admin );
    }


}
