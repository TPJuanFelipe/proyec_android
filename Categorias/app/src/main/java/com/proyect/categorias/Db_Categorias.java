package com.proyect.categorias;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Db_Categorias extends SQLiteOpenHelper {

    private static  final  String DATABASE_NOMBRE = "database_Categorias";

    private static final int DABASE_VERSION = 1;
    private static final String TABLA_CATEGORIAS = "categorias";
    private static final String COLUMNA_ID = "id_categoria";
    private static final String COLUMNA_NOMBRE = "nombre_categoria";



    public Db_Categorias (Context context){
        super(context,DATABASE_NOMBRE, null, DABASE_VERSION );

    }

    @Override
    public void onCreate ( SQLiteDatabase db ) {
        String Crear_Categorias= "CREATE TABLE " + TABLA_CATEGORIAS + "(" +
                COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMNA_NOMBRE + " TEXT)";

        db.execSQL ( Crear_Categorias );
    }

    @Override
    public void onUpgrade ( SQLiteDatabase db, int i, int i1 ) {
        String Eliminar_tabla = "DROP TABLE IF EXISTS " + TABLA_CATEGORIAS;
        db.execSQL ( Eliminar_tabla );
        onCreate ( db );



    }

    public void insertItem(Items_Categorias item){
        SQLiteDatabase db = this.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ( COLUMNA_NOMBRE, item.getNombre () );
        db.insert ( TABLA_CATEGORIAS, null , values );
        db.close();

    }

    public List<Items_Categorias> getAllItem(){
        List<Items_Categorias> ListItem = new ArrayList<> ();
        SQLiteDatabase db = this.getReadableDatabase ();

        String guardar = " SELECT * FROM " + TABLA_CATEGORIAS;
        Cursor cursor = db.rawQuery ( guardar, null );
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMNA_ID));
                @SuppressLint("Range") String nombre = cursor.getString ( cursor.getColumnIndex ( COLUMNA_NOMBRE ) );

                Items_Categorias item = new Items_Categorias ();
                item.setId ( id );
                item.setNombre ( nombre );

                ListItem.add ( item );
            } while (cursor.moveToNext ());
        }

        cursor.close();
        db.close();

        return ListItem;
    }
    public void updateItem(Items_Categorias item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_NOMBRE, item.getNombre());

        db.update(TABLA_CATEGORIAS, values, COLUMNA_ID + " = ?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public void deleteItem(Items_Categorias item) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLA_CATEGORIAS, COLUMNA_ID + "=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

}
