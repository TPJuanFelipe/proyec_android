package com.proyect.login_y_registro;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Inicio_sesion extends Fragment {
Context context;
    EditText edt_correo_login;
    EditText edt_contrasena_login;
    ImageView btn_inicar_sesion;

Db_login db_login;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view= inflater.inflate ( R.layout.fragment_inicio_sesion, container, false );
        db_login = new Db_login ( getContext () );

        edt_correo_login = view.findViewById ( R.id.edt_correo_inicioSesion );
        edt_contrasena_login= view.findViewById ( R.id.edt_contrasena_inicioSesion );
        btn_inicar_sesion = view.findViewById ( R.id.btn_iniciar_sesion );

        btn_inicar_sesion.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                login consulta= new login ();
                consulta.setCorreo_Usu ( edt_correo_login.getText ().toString () );
                consulta.setContrasena ( edt_contrasena_login.getText ().toString () );
                db_login.iniciar_sesion ( consulta );


            }
        } );






        return view;
    }


}