package com.proyect.categorias;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Db_Categorias extends SQLiteOpenHelper {

    private static  final  String DATABASE_NOMBRE = "database_Categorias";

    private static final int DABASE_VERSION = 4;
    private static final String TABLA_CATEGORIAS = "categorias";
    private static final String COLUMNA_ID = "id_categoria";
    private static final String COLUMNA_NOMBRE = "nombre_categoria";

    private static final String TABLE_NAME = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_CANTIDAD = "cantidad";
    private static final String COLUMN_AGOTADO = "agotado";

    public Db_Categorias ( Context context){
        super(context,DATABASE_NOMBRE, null, DABASE_VERSION );

    }

    @Override
    public void onCreate ( SQLiteDatabase db ) {
        String Crear_Categorias= "CREATE TABLE " + TABLA_CATEGORIAS + "(" +
                COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMNA_NOMBRE + " TEXT)";

        db.execSQL ( Crear_Categorias );
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NOMBRE + " TEXT," +
                COLUMN_CANTIDAD + " INTEGER," +
                COLUMN_AGOTADO + " INTEGER) ";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade ( SQLiteDatabase db, int i, int i1 ) {
        String Eliminar_tabla = "DROP TABLE IF EXISTS " + TABLA_CATEGORIAS + TABLE_NAME;
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
    public void insertItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, item.getNombre());
        values.put(COLUMN_CANTIDAD, item.getCantidad());
        values.put(COLUMN_AGOTADO, item.isAgotado() ? 1 : 0);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE));
                @SuppressLint("Range") int cantidad = cursor.getInt(cursor.getColumnIndex(COLUMN_CANTIDAD));
                @SuppressLint("Range") boolean agotado = cursor.getInt(cursor.getColumnIndex(COLUMN_AGOTADO)) == 1;

                Item item = new Item();
                item.setId(id);
                item.setNombre(nombre);
                item.setCantidad(cantidad);
                item.setAgotado(agotado);

                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemList;
    }

    public void updateItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, item.getNombre());
        values.put(COLUMN_CANTIDAD, item.getCantidad());
        values.put(COLUMN_AGOTADO, item.isAgotado() ? 1 : 0);
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public void deleteItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

}
