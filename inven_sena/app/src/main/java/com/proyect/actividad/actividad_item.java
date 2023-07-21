package com.proyect.actividad;

import android.os.Parcel;
import android.os.Parcelable;

public class actividad_item implements Parcelable {
    int id_trabajo;
    String trabajo_realizado;
    String fecha_realizado;
    int id_trabajador;


    public int getId_trabajo () {
        return id_trabajo;
    }

    public String getTrabajo_realizado () {
        return trabajo_realizado;
    }

    public String getFecha_realizado () {
        return fecha_realizado;
    }

    public int getId_trabajador () {
        return id_trabajador;
    }

    public void setId_trabajo ( int id_trabajo ) {
        this.id_trabajo = id_trabajo;
    }

    public void setTrabajo_realizado ( String trabajo_realizado ) {
        this.trabajo_realizado = trabajo_realizado;
    }

    public void setFecha_realizado ( String fecha_realizado ) {
        this.fecha_realizado = fecha_realizado;
    }

    public void setId_trabajador ( int id_trabajador ) {
        this.id_trabajador = id_trabajador;
    }

    @Override
    public String toString () {
        return "actividad_item{" +
                "id_trabajo=" + id_trabajo +
                ", trabajo_realizado='" + trabajo_realizado + '\'' +
                ", fecha_realizado='" + fecha_realizado + '\'' +
                ", id_trabajador=" + id_trabajador +
                '}';
    }

    public actividad_item(){}
    protected actividad_item ( Parcel in ) {
        id_trabajo = in.readInt ();
        trabajo_realizado = in.readString ();
        fecha_realizado = in.readString ();
        id_trabajador = in.readInt ();
    }

    @Override
    public void writeToParcel ( Parcel dest, int flags ) {
        dest.writeInt ( id_trabajo );
        dest.writeString ( trabajo_realizado );
        dest.writeString ( fecha_realizado );
        dest.writeInt ( id_trabajador );
    }

    @Override
    public int describeContents () {
        return 0;
    }

    public static final Creator<actividad_item> CREATOR = new Creator<actividad_item> () {
        @Override
        public actividad_item createFromParcel ( Parcel in ) {
            return new actividad_item ( in );
        }

        @Override
        public actividad_item[] newArray ( int size ) {
            return new actividad_item[size];
        }
    };


}
