package com.proyect.inicioSesion_y_registro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyect.inven_sena.R;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class registro extends AppCompatActivity {
    String[] identi_datos= {"CC","CE","NIP","NIT","TI","PAP"};
    Spinner sp_identifi_tbjd;
    EditText edt_identifi_tbjd, edt_nombreComp_tbjd, edt_correo_tbjd, edt_tipo_identifi,
             edt_usu_tbjd, edt_contra_tbjd, edt_confi_contra_tbjd;
    Button btn_regirar_usu_server;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registro );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, identi_datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_identifi_tbjd = findViewById (  R.id.sp_tipo_identifi_tbjd );
        edt_identifi_tbjd = findViewById ( R.id.edt_identifi_tbjd );
        edt_nombreComp_tbjd = findViewById ( R.id.edt_nombreComp_tbjd );
        edt_correo_tbjd = findViewById ( R.id.edt_correo_tbjd );
        edt_usu_tbjd = findViewById ( R.id.edt_usuario_tbjd );
        edt_contra_tbjd = findViewById ( R.id.edt_contra_tbjd );
        btn_regirar_usu_server = findViewById ( R.id.btn_registrar_usu_Server );
        sp_identifi_tbjd.setAdapter(adapter);



        btn_regirar_usu_server.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String  identi_tbjd= edt_identifi_tbjd.getText ().toString ();
                String tipo_identifi = (String) sp_identifi_tbjd.getSelectedItem();
                String nombre_Comp = edt_nombreComp_tbjd.getText ().toString ();
                String  correo = edt_correo_tbjd.getText ().toString ();
                String  usu_tbjd = edt_usu_tbjd.getText ().toString ();
                String  contra_tbjd = edt_contra_tbjd.getText ().toString ();
                login registrar_usu = new login ();
                registrar_usu.setId_identi_tbj ( Integer.parseInt ( identi_tbjd ) );
                registrar_usu.setTipo_docu_tbj (tipo_identifi );
                registrar_usu.setNombre_tbj ( nombre_Comp );
                registrar_usu.setCorreo_tbj ( correo );
                registrar_usu.setUsu_tbj ( usu_tbjd );
                registrar_usu.setContra_tbj ( contra_tbjd );
                registro_usu ( getApplicationContext (), registrar_usu );


            }
        } );





    }

    public void registro_usu(Context context, login item) {
        String url = "http://192.168.43.79:80/conexion_android_login/registrar_usu.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String status = jsonResponse.getString("status");

                            if (status.equals("success")) {
                                Intent ir = new Intent (registro.this, inicio_sesion.class);
                                startActivity ( ir );

                                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            } else {

                                String message = jsonResponse.getString("message");
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {

                            Toast.makeText(context, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al registrar usuario: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("identificacion", String.valueOf(item.getId_identi_tbj()));
                params.put("tipo_identificacion", item.getTipo_docu_tbj());
                params.put("nombre_completo", item.getNombre_tbj());
                params.put("correo", item.getCorreo_tbj());
                params.put("usuario", item.getUsu_tbj());
                params.put("contrasena", item.getContra_tbj());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}