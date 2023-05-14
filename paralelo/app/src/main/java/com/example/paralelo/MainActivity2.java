package com.example.paralelo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paralelo.ui.login.LoginFragment;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );
        TextView tvInfo = findViewById(R.id.tvInfo);
        Button Fragment1=(Button) findViewById ( R.id.btnFra );

        String informacion = getIntent().getStringExtra("informacion");
        tvInfo.setText(informacion);


        Fragment1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Toast.makeText ( MainActivity2.this,"mensaje",Toast.LENGTH_LONG ).show ();
                Fragment  mifra=new LoginFragment ();
                FragmentManager MiFma=getSupportFragmentManager ();
                FragmentTransaction FraTra=MiFma.beginTransaction ();
                FraTra.replace ( R.id.lyContenedor,mifra );

            }
        } );



    }
}