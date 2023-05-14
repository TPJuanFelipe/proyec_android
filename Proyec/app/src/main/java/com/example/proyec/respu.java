package com.example.proyec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class respu extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_respu );

        TextView Respu=(TextView) findViewById ( R.id.xd );
        if (getIntent ()!=null && getIntent ().hasExtra ( Intent.EXTRA_TEXT )){
            Respu.setText ( getIntent ().getStringExtra ( Intent.EXTRA_TEXT) );
        }
    }

    public  void  XDDD( View view ){
        finish ();

    }



}