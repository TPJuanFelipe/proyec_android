package com.proyect.nuevo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 3;



    static final String TABLE_USERS = "users";
    protected static final String COLUMN_ID = "_id";
    static final String COLUMN_CORREO = "correo";
    static final String COLUMN_USERNAME = "usuario";
    static final String COLUMN_PASSWORD = "password";


    static final String COLUMN_PASSWORD_RE ="Re_password";



    // Sentencia SQL para crear la tabla de usuarios
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT, "
            + COLUMN_CORREO + " TEXT, "
            + COLUMN_PASSWORD + " TEXT, "

            + COLUMN_PASSWORD_RE + " TEXT)";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USERS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }
}

