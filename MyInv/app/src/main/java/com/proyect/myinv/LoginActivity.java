package com.proyect.myinv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText RegistUsua;
    private EditText RegisPassw;
    private ImageView buttonLogin;
    private ImageView buttonRegister;
    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "databaseLogin";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button irRe=findViewById ( R.id.registForm );

        editTextUsername = findViewById(R.id.mEmailView);
        editTextPassword = findViewById(R.id.mPasswordView);
        RegistUsua=findViewById ( R.id.Usuario );
        RegisPassw=findViewById ( R.id.passwordw );

        buttonLogin = findViewById(R.id.imagLogin);
        buttonRegister = findViewById(R.id.imageViewmenu);

        irRe.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent ir =new Intent (LoginActivity.this, RegistroActivity.class);
                startActivity ( ir );
            }
        } );


        database = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        createTable();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (checkUserCredentials(username, password)) {
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent ir =new Intent (LoginActivity.this,menuActivity.class);
                    startActivity ( ir );

                } else {
                    Toast.makeText(LoginActivity.this, "Ingreso inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        database.execSQL(createTableQuery);
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
