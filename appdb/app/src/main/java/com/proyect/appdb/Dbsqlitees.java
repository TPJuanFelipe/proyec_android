package com.proyect.appdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbsqlitees extends SQLiteOpenHelper {

    public static final String DATABASE_NOMBRE = "db_lite";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLA_PRODUCTOS = "productos";
    public static final String COLUMNA_ID = "id";
    public  static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_CANTIDAD = "cantidad";

    public Dbsqlitees(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String crear_tablas = "CREATE TABLE " + TABLA_PRODUCTOS + " (" +
                COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_NOMBRE + " TEXT, " +
                COLUMNA_CANTIDAD + " INTEGER)";
        sqLiteDatabase.execSQL(crear_tablas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String eliminar_tabla = "DROP TABLE IF EXISTS " + TABLA_PRODUCTOS;
        sqLiteDatabase.execSQL(eliminar_tabla);
        onCreate(sqLiteDatabase);
    }


    public String [][] consultar_db(Context context, String cadenasql){
        String [][] datos = null;

        Dbsqlitees dbConectar= new Dbsqlitees ( context, DATABASE_NOMBRE, null, DATABASE_VERSION );
        SQLiteDatabase



    }
}
