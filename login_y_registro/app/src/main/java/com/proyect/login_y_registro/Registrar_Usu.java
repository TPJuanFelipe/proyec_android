package com.proyect.login_y_registro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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


public class Registrar_Usu extends Fragment {
Context context;
    private EditText edt_identifi;
    private EditText edt_tipo_identifi;
    private EditText edt_nombre_traba;
    private EditText edt_correo;
    private EditText edt_usuario;
    private EditText edt_contrasena;
    private ImageView btn_registrar_usu;
    login login;
    Db_login db_login;




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_registrar__usu, container, false );
        db_login =new Db_login (  getContext ());

        edt_identifi= view.findViewById(R.id.edt_identifi);
        edt_tipo_identifi= view.findViewById(R.id.edt_tipo_identifi);
        edt_nombre_traba = view.findViewById(R.id.edt_nombre_traba);
        edt_correo = view.findViewById ( R.id.edt_correo_traba );
        edt_usuario = view.findViewById ( R.id.edt_usuario );
        edt_contrasena = view.findViewById (  R.id.edt_contrasena );
        btn_registrar_usu = view.findViewById(R.id.btn_iniciar_sesion );

        btn_registrar_usu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identifi = edt_identifi.getText ().toString ().trim ();
                String Tipo_identifi = edt_tipo_identifi.getText ().toString ().trim ();
                String nombre =  edt_nombre_traba.getText ().toString ().trim ();
                String correo =  edt_correo.getText ().toString ().trim ();
                String usu =  edt_usuario.getText ().toString ().trim ();
                String contra =  edt_contrasena.getText ().toString ().trim ();

                if(!identifi.isEmpty () && !Tipo_identifi.isEmpty () && !nombre.isEmpty () &&
                   !correo.isEmpty () && !usu.isEmpty () && !contra.isEmpty ()){

                    login insert_usu = new login();
                    insert_usu.setIdentifi (Integer.parseInt ( edt_identifi.getText ().toString () ));
                    insert_usu.setTipo_identifi ( edt_tipo_identifi.getText ().toString () );
                    insert_usu.setNombre_Usu ( edt_nombre_traba.getText ().toString () );
                    insert_usu.setCorreo_Usu (edt_correo.getText ().toString ());
                    insert_usu.setUsuario ( edt_usuario.getText ().toString () );
                    insert_usu.setContrasena ( edt_contrasena.getText ().toString () );
                    db_login.registro_usu ( insert_usu );
                    Toast.makeText ( getActivity (), "registro acaptado", Toast.LENGTH_SHORT ).show ();
                    Fragment frag= new Inicio_sesion ();
                    FragmentManager fragM=  getParentFragmentManager ();

                    FragmentTransaction fragT = fragM.beginTransaction ();
                    fragT.replace ( R.id.conte_login , frag );
                    fragT.commit ();

                }else{

                  Toast.makeText ( getActivity (), "Por favor llener todos los campos", Toast.LENGTH_SHORT ).show ();


                }





            }
        });
        return view;
    }




}
