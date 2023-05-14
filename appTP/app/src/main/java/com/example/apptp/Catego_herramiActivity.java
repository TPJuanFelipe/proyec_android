package com.example.apptp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Catego_herramiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_catego_herrami );
    }



    public void irEletri(View view) {
        Intent interno = new Intent( Catego_herramiActivity.this, Herra_eletriActivity.class );
        startActivity( interno );
    }





}