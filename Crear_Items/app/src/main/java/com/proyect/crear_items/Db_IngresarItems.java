package com.proyect.crear_items;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Db_IngresarItems extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "item.db";
    private static final int DATABASE_VERSION = 5;
    //...
    private static final String TABLE_NAME = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_CANTIDAD = "cantidad";
    private static final String COLUMN_AGOTADO = "agotado";
    //.....


    public Db_IngresarItems(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NOMBRE + " TEXT," +
                COLUMN_CANTIDAD + " INTEGER," +
                COLUMN_AGOTADO + " INTEGER) "
        ;


        db.execSQL(createTableQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);


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