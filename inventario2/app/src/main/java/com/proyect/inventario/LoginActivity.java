package com.proyect.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private SQLiteDatabase database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        editTextUsername = findViewById ( R.id.mEmailView );
        editTextPassword = findViewById ( R.id.mPasswordView );
        buttonLogin = findViewById ( R.id.mEmailSignInButton );
        buttonRegister = findViewById ( R.id.Registro );

        Button registrar=findViewById ( R.id.registForm );

        registrar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent ir=new Intent (LoginActivity.this,Registro.class);
                startActivity ( ir );
            }
        } );

        // Establecer la conexión con la base de datos
        database = openOrCreateDatabase ( "my_database", MODE_PRIVATE, null );
        database.execSQL ( "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR, password VARCHAR)" );



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Verificar si el usuario ya existe en la base de datos
                Cursor cursor = database.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
                if (cursor.getCount() > 0) {
                    Toast.makeText(LoginActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    // Insertar el nuevo usuario en la base de datos
                    database.execSQL("INSERT INTO users (username, password) VALUES (?, ?)", new String[]{username, password});
                    Toast.makeText(LoginActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Verificar las credenciales en la base de datos
                Cursor cursor = database.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
                if (cursor.getCount() > 0) {
                    // Las credenciales son válidas, redirigir al usuario a la siguiente actividad
                    Intent intent = new Intent (LoginActivity.this, menuActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza la actividad actual
                } else {
                    // Las credenciales son incorrectas, mostrar un mensaje de error
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la conexión con la base de datos
        if (database != null) {
            database.close();
        }
    }
    }
