package com.proyect.login_y_registro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

ImageView ir_frag_login;
ImageView ir_login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        ir_frag_login = findViewById ( R.id.ir_login );
        ir_login= findViewById ( R.id.ir_login );

        ir_frag_login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                openItemFragment ();
            }
        } );

        ir_login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                iniciar_sesion_login ();
            }
        } );


    }

    private void openItemFragment() {
        Fragment itemFragment = new Registrar_Usu ();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte_login, itemFragment);
        fragmentTransaction.commit();
    }

    private void iniciar_sesion_login() {
        Fragment itemFragment = new Inicio_sesion ();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte_login, itemFragment);
        fragmentTransaction.commit();
    }


}