package com.proyect.herramientas_inv;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db_Herra  {
    Context context;

    public Db_Herra(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public void insert_Item_Herra(Item_Herramienta item) {
        String url = "http://192.168.43.79:80/conexion_android_herramienta/insertar_Herramientas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Registro insertado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al insertar el registro: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put("id_productos", String.valueOf(item.getId_Herra ()));
                params.put("nombre", item.getNombre_Herra ());
                params.put ( "descripcion" , item.getDescripcion_Herra ());
                params.put("cantidad", String.valueOf ( item.getCantidad_Herra () ));
                params.put("imagen", String.valueOf ( item.getImagen_Herra () ));
                params.put("id_categoria", String.valueOf ( item.getId_cate () ));
                params.put("identifi_admin", String.valueOf(item.getCodigo_admin ()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void getAllItems( Response.Listener<List<Item_Herramienta>> successListener, Response.ErrorListener errorListener) {
        String url = "http://192.168.101.75:80/conexion_android/consultar_Herramientas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Item_Herramienta> itemList = new ArrayList<> ();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id_productos = jsonObject.getInt("id_productos");
                                String nombre = jsonObject.getString("nombre");
                                int cantidad= jsonObject.getInt ( "cantidad" );


                                Item_Herramienta item = new Item_Herramienta ();
                                item.setId_Herra (id_productos);
                                item.setNombre_Herra (nombre);
                                item.setCantidad_Herra (cantidad);


                                itemList.add(item);
                            }
                            successListener.onResponse(itemList);
                        } catch (JSONException e) {
                            errorListener.onErrorResponse(new VolleyError("Error al analizar la respuesta JSON"));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    public void modifi_Item_Herra(Item_Herramienta item) {
        String url = "http://192.168.101.75:80/conexion_android/modificar_Herramientas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Herramieta insertada correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al insertar la herramienta: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put("id_productos", String.valueOf(item.getId_Herra ()));
                params.put("nombre", item.getNombre_Herra ());
                params.put ( "descripcion" , item.getDescripcion_Herra ());
                params.put("cantidad", String.valueOf ( item.getCantidad_Herra () ));
                params.put("imagen", String.valueOf ( item.getImagen_Herra () ));
                params.put("identifi_admin", String.valueOf(item.getCodigo_admin ()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
