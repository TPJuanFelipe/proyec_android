package com.proyect.herramientas_inv;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Item_Herramienta implements Parcelable {
    private int id_Herra;
    private String nombre_Herra;
    private String descripcion_Herra;
    private int cantidad_Herra;
    private String imagen_Herra;
    private int id_cate;
    private int codigo_admin;

    public Item_Herramienta () {

    }

    protected Item_Herramienta ( Parcel in ) {
        id_Herra= in.readInt ();
        nombre_Herra= in.readString ();
        descripcion_Herra= in.readString ();
        cantidad_Herra= in.readInt ();
        imagen_Herra= in.readString ();
        id_cate= in.readInt ();
        codigo_admin= in.readInt ();
    }

    public static final Creator<Item_Herramienta> CREATOR = new Creator<Item_Herramienta> () {
        @Override
        public Item_Herramienta createFromParcel ( Parcel in ) {
            return new Item_Herramienta ( in );
        }

        @Override
        public Item_Herramienta[] newArray ( int size ) {
            return new Item_Herramienta[size];
        }
    };




    public int getId_Herra () {
        return id_Herra;
    }

    public String getNombre_Herra () {
        return nombre_Herra;
    }

    public String getDescripcion_Herra () {
        return descripcion_Herra;
    }

    public int getCantidad_Herra () {
        return cantidad_Herra;
    }

    public String getImagen_Herra () {
        return imagen_Herra;
    }

    public int getId_cate () {
        return id_cate;
    }

    public int getCodigo_admin () {
        return codigo_admin;
    }
    //


    public void setId_Herra ( int id_Herra ) {
        this.id_Herra = id_Herra;
    }

    public void setNombre_Herra ( String nombre_Herra ) {
        this.nombre_Herra = nombre_Herra;
    }

    public void setDescripcion_Herra ( String descripcion_Herra ) {
        this.descripcion_Herra = descripcion_Herra;
    }

    public void setCantidad_Herra ( int cantidad_Herra ) {
        this.cantidad_Herra = cantidad_Herra;
    }

    public void setImagen_Herra ( String imagen_Herra ) {
        this.imagen_Herra = imagen_Herra;
    }

    public void setId_cate ( int id_cate ) {
        this.id_cate = id_cate;
    }

    public void setCodigo_admin ( int codigo_admin ) {
        this.codigo_admin = codigo_admin;
    }

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( @NonNull Parcel parcel, int i ) {
        parcel.writeInt ( id_Herra );
        parcel.writeString ( nombre_Herra );
        parcel.writeString ( descripcion_Herra );
        parcel.writeInt ( cantidad_Herra );
        parcel.writeString ( imagen_Herra );
        parcel.writeInt ( id_cate );
        parcel.writeInt ( codigo_admin );
    }
}
