package com.proyect.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    EditText edt_nombre;
    EditText edt_ficha;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        edt_nombre = findViewById ( R.id.edt_usuario );
        edt_ficha = findViewById ( R.id.edt_contraseña );
        Button guardar= findViewById ( R.id.Guardar);

        guardar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                guardar ();
            }
        } );


        SharedPreferences prefer= getSharedPreferences ( "FelipeTP", MODE_PRIVATE );
        String nombre = prefer.getString ( "usuario","" );
        String ficha = prefer.getString ("contraseña","");
        edt_nombre.setText ( nombre );
        edt_ficha.setText ( ""+ficha );
    }


    public void guardar (  ){
        String Usuario = edt_nombre.getText ().toString ();
        String contraseña = edt_ficha.getText ().toString ();

        SharedPreferences mi_prefere = getSharedPreferences ( "FelipeTP", MODE_PRIVATE);
        SharedPreferences.Editor editar = mi_prefere.edit ();
        editar.putString ( "usuario", Usuario );
        editar.putString ("contraseña",contraseña);
        editar.commit();
    }
}