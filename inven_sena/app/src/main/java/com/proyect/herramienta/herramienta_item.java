package com.proyect.herramienta;

import android.os.Parcel;
import android.os.Parcelable;

public class herramienta_item implements Parcelable {
    int id_herra;
    String nombre;
    String descripcion;
    int cantidad;
    String imagen;
    int id_categoria;
    int codigo_admin;

    public herramienta_item(){}

    protected herramienta_item ( Parcel in ) {
        id_herra = in.readInt ();
        nombre = in.readString ();
        descripcion = in.readString ();
        cantidad = in.readInt ();
        imagen = in.readString ();
        id_categoria = in.readInt ();
        codigo_admin = in.readInt ();
    }

    @Override
    public void writeToParcel ( Parcel dest, int flags ) {
        dest.writeInt ( id_herra );
        dest.writeString ( nombre );
        dest.writeString ( descripcion );
        dest.writeInt ( cantidad );
        dest.writeString ( imagen );
        dest.writeInt ( id_categoria );
        dest.writeInt ( codigo_admin );
    }

    @Override
    public int describeContents () {
        return 0;
    }

    public static final Creator<herramienta_item> CREATOR = new Creator<herramienta_item> () {
        @Override
        public herramienta_item createFromParcel ( Parcel in ) {
            return new herramienta_item ( in );
        }

        @Override
        public herramienta_item[] newArray ( int size ) {
            return new herramienta_item[size];
        }
    };

    public int getId_herra () {
        return id_herra;
    }

    public String getNombre () {
        return nombre;
    }

    public String getDescripcion () {
        return descripcion;
    }

    public int getCantidad () {
        return cantidad;
    }

    public String getImagen () {
        return imagen;
    }

    public int getId_categoria () {
        return id_categoria;
    }

    public int getCodigo_admin () {
        return codigo_admin;
    }

    public void setId_herra ( int id_herra ) {
        this.id_herra = id_herra;
    }

    public void setNombre ( String nombre ) {
        this.nombre = nombre;
    }

    public void setDescripcion ( String descripcion ) {
        this.descripcion = descripcion;
    }

    public void setCantidad ( int cantidad ) {
        this.cantidad = cantidad;
    }

    public void setImagen ( String imagen ) {
        this.imagen = imagen;
    }

    public void setId_categoria ( int id_categoria ) {
        this.id_categoria = id_categoria;
    }

    public void setCodigo_admin ( int codigo_admin ) {
        this.codigo_admin = codigo_admin;
    }

    @Override
    public String toString () {
        return "herramienta_item{" +
                "id_herra=" + id_herra +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", imagen='" + imagen + '\'' +
                ", id_categoria=" + id_categoria +
                ", codigo_admin=" + codigo_admin +
                '}';
    }
}
