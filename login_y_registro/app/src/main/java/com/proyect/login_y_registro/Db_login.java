package com.proyect.login_y_registro;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Db_login {

    Context context;

    public Db_login ( Context context ) {
        this.context = context;
    }

    public void registro_usu(login item) {
        String url = "http://192.168.101.76:8018/conexion_android_login/registro_login.php";
        RequestQueue requestQueue = Volley.newRequestQueue ( context );

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Verificar la respuesta del servidor
                        if (response.equals("puedes iniciar sesion")) {
                            // Acciones si se puede iniciar sesión
                            Toast.makeText(context, "Puedes iniciar sesión", Toast.LENGTH_SHORT).show();


                        } else {
                            // Acciones si no se puede iniciar sesión
                            Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al iniciar sesión: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();

                params.put("identificacion", String.valueOf ( item.getIdentifi () ) );
                params.put("Tipo_identificacion", item.getTipo_identifi ());
                params.put("nombre_completo", item.getNombre_Usu ());
                params.put("correo", item.getCorreo_Usu ());
                params.put ( "usuario", item.getUsuario () );
                params.put("contrasena", item.getContrasena ());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    public void iniciar_sesion1(login item) {
        String url = "http://192.168.101.76:8018/conexion_android_login/consulta_login.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Verificar la respuesta del servidor
                        if (response.equals("puedes iniciar sesion")) {
                            // Acciones si se puede iniciar sesión
                            Toast.makeText(context, "Puedes iniciar sesión", Toast.LENGTH_SHORT).show();

                        } else {
                            // Acciones si no se puede iniciar sesión
                            Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al iniciar sesión: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();

                params.put("correo", item.getCorreo_Usu());

                params.put("contrasena", item.getContrasena());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void iniciar_sesion(login item) {
        String url = "http://192.168.101.76:8018/conexion_android_login/consulta_login.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Manejar la respuesta del servidor
                        if (response.equals("success")) {
                            // Acciones si el inicio de sesión es exitoso
                            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("invalid")) {
                            // Acciones si las credenciales son inválidas
                            Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                        } else {
                            // Acciones si hay un error en la respuesta
                            Toast.makeText(context, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Acciones si hay un error en la solicitud
                        Toast.makeText(context, "Error al iniciar sesión: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("correo", item.getCorreo_Usu());

                params.put("contrasena", item.getContrasena());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }



}
