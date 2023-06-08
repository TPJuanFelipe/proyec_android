package com.proyect.categorias;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Items_Categorias implements Parcelable {

    private int id;
    private String nombre;

    public Items_Categorias(){}

    protected Items_Categorias (Parcel in){
        id= in.readInt ();
        nombre= in.readString ();

    }
    public static final Creator<Items_Categorias> CREATOR = new Creator<Items_Categorias> () {
        @Override
        public Items_Categorias createFromParcel ( Parcel in  ) {
            return new Items_Categorias (in);
        }

        @Override
        public Items_Categorias[] newArray ( int size ) {
            return new Items_Categorias[size];
        }
    };

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre ( String nombre ) {
        this.nombre = nombre;
    }



    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( @NonNull Parcel parcel, int i ) {
        parcel.writeInt ( id );
        parcel.writeString ( nombre );

    }
}
