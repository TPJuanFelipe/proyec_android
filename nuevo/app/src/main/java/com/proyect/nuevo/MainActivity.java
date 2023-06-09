package com.proyect.nuevo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView Login ;

Button Registro;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );




        Login=findViewById ( R.id.login );
        Login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Fragment mifra= new loginFragment2 ();
                FragmentManager FraM= getSupportFragmentManager ();
                FragmentTransaction FraT=FraM.beginTransaction ();
                FraT.replace ( R.id.conte, mifra );
                FraT.commit ();
            }
        } );








    }


}