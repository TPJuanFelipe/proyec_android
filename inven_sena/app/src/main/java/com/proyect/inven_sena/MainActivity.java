package com.proyect.inven_sena;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.proyect.inicioSesion_y_registro.inicio_sesion;
import com.proyect.inicioSesion_y_registro.registro;

public class MainActivity extends AppCompatActivity {
    Button ir_login, btn_ir_registro;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        ir_login= findViewById ( R.id.btn_ir_login );
        btn_ir_registro= findViewById ( R.id.btn_registrar_usu );

        ir_login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent ir = new Intent ( MainActivity.this, inicio_sesion.class);
                startActivity ( ir );
            }
        } );

        btn_ir_registro.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent ir = new Intent ( MainActivity.this, registro.class);
                startActivity ( ir );
            }
        } );

    }
}