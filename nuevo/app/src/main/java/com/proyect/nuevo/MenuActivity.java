package com.proyect.nuevo;

import static androidx.core.content.ContentProviderCompat.requireContext;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.snackbar.Snackbar;

public class MenuActivity extends AppCompatActivity {
    VideoView Vv;

    private TextView txtUsuPerfil;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        txtUsuPerfil = findViewById(R.id.usu_Perfil);


        StringBuilder sb = new StringBuilder();
        String currentUserID = "1"; // Reemplaza con la ID del usuario actualmente conectado
        Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.COLUMN_USERNAME +
                        " FROM " + DatabaseHelper.TABLE_USERS +
                        " WHERE " + DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{currentUserID});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
                sb.append(username).append(" ");  // Agregar el nombre de usuario al StringBuilder
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        String usuarios = sb.toString();
        if (!usuarios.isEmpty()) {
            usuarios = usuarios.substring(0, usuarios.length() - 1);
        }

        txtUsuPerfil.setText(usuarios);
        ImageView regresar_inicio;
        regresar_inicio = findViewById ( R.id.regresarInicio );
        regresar_inicio.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                InfoSalir ();
            }
        } );





        ImageView Herra = findViewById(R.id.Imaherra);
        Herra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(MenuActivity.this, Categoria_HerraActivity.class);
                startActivity(ir);
            }
        });
    }
    private void InfoSalir() {
        AlertDialog.Builder builder = new AlertDialog.Builder ( MenuActivity.this );
        builder.setTitle("Cerrar sesion");
        builder.setMessage("¿Estás seguro de que quieres cerrar sesion?\uD83D\uDE2D\uD83E\uDD79\uD83E\uDD72 ");
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent ir = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(ir);
                finish ();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

     public void metodoInstru(View view){

         Snackbar mensaje= Snackbar.make ( view,"¿Porque me quieres dejar? ", Snackbar.ANIMATION_MODE_SLIDE );
         mensaje.setAction ( "Aceptar", new View.OnClickListener () {
             @Override
             public void onClick ( View view ) {
                 Toast.makeText ( MenuActivity.this, "Saliendo", Toast.LENGTH_SHORT ).show ();
                 Intent ir = new Intent(MenuActivity.this, MainActivity.class);
                 startActivity(ir);
                 finish ();
             }
         } );
         mensaje.show ();
     }



}
