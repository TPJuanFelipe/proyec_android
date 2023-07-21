package com.proyect.inicioSesion_y_registro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.proyect.inven_sena.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class inicio_sesion extends AppCompatActivity {

    EditText edt_correo_tbj, edt_contra_tbj;
    Button btn_iniciar_sesion;
Db_login db_login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_inicio_sesion );
        db_login = new Db_login ();
        edt_correo_tbj= findViewById ( R.id.edt_correo_tbjb_ini );
        edt_contra_tbj= findViewById ( R.id.edt_contra_tbjd_ini );
        btn_iniciar_sesion= findViewById ( R.id.btn_iniciar_sesion_tbj );
        btn_iniciar_sesion.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String correo = edt_correo_tbj.getText ().toString ();
                String contra = edt_contra_tbj.getText ().toString ();

                login ini_usu= new login ();

                ini_usu.setCorreo_tbj ( correo );
                ini_usu.setContra_tbj ( contra );
                iniciar_sesion (getApplicationContext (), ini_usu );




            }
        } );
    }

    public void iniciar_sesion(Context context, login item) {
        String url = "http://192.168.43.79:80/conexion_android_login/iniciar_sesion_usu.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String status = jsonResponse.getString("status");
                            String message = jsonResponse.getString("message");

                            if (status.equals("success")) {
                                // Acciones si el inicio de sesión es exitoso
                                Intent ir= new Intent (inicio_sesion.this, menu_inv.class);
                                startActivity ( ir );

                                Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            } else if (status.equals("error")) {
                                // Acciones si las credenciales son inválidas
                                Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                            } else {
                                // Acciones si hay un error en la respuesta
                                Toast.makeText(context, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // Error al analizar la respuesta JSON
                            Toast.makeText(context, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        // Acciones si hay un error en la solicitud
                        Toast.makeText(context, "Error al iniciar sesión: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();

                params.put("correo", item.getCorreo_tbj ());
                params.put("contrasena", item.getContra_tbj ());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}