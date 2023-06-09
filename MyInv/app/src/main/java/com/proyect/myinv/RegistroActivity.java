package com.proyect.myinv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    private ImageView buttonRegister;
    private SQLiteDatabase database;
    private static final String DATABASE_NAME = "databaseLogin";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registro );

        editTextUsername = findViewById(R.id.Usuario);
        editTextPassword = findViewById(R.id.passwordw);
        buttonRegister = findViewById(R.id.imageViewmenu);

        database = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        createTable();






    }

    private void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        database.execSQL(createTableQuery);
    }

    private boolean insertUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = database.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    private boolean checkUserCredentials(String username, String password) {
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }



}