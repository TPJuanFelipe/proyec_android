package com.proyect.inicioSesion_y_registro;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

public class Db_login {

    Context context;

    public void iniciar_sesion(login item) {
        String url = "http://10.185.144.16:8018/conexion_android_login/consulta_login.php";
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

                params.put("correo", item.getNombre_tbj ());
                params.put("contrasena", item.getContra_tbj ());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
