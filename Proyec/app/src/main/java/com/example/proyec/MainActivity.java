package com.example.proyec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
    }
    public void ir_a_pagina_web( View view ){
        Intent Ir=new Intent (Intent.ACTION_VIEW);
        Ir.setData( Uri.parse ( "https://www3.animeflv.net/" ));
        startActivity ( Ir );



    };
    public void  enviar(View view){
        EditText envi=(EditText) findViewById ( R.id.editTextTextPersonName );
        String texto= envi.getText ().toString ();
        Intent Ir= new Intent (MainActivity.this,respu.class);
        Ir.putExtra ( Intent.EXTRA_TEXT, texto);
        startActivity ( Ir );

    };
}