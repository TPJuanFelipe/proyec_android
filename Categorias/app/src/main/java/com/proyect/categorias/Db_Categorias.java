package com.proyect.categorias;

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

public class Db_categorias {
Context context;

    public Db_categorias(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public void insertItem(Items_Categorias item) {
        String url = "http://192.168.101.75:80/conexion_android/insertar_Categorias.php";
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
                params.put("id_categoria", String.valueOf(item.getId_categoria()));
                params.put("categorias", item.getCategorias());
                params.put("identifi_admin", String.valueOf(item.getIdentifi_admin()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void getAllItems( Response.Listener<List<Items_Categorias>> successListener, Response.ErrorListener errorListener) {
        String url = "http://192.168.101.75:80/conexion_android/consultar_categoria.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Items_Categorias> itemList = new ArrayList<> ();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id_categoria = jsonObject.getInt("id_categoria");
                                String categorias = jsonObject.getString("categorias");
                                int identifi_admin = jsonObject.getInt("identifi_admin");

                                Items_Categorias item = new Items_Categorias();
                                item.setId_categoria(id_categoria);
                                item.setCategorias(categorias);
                                item.setIdentifi_admin(identifi_admin);

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


    public void updateItem(Items_Categorias item) {
        String url = "http://192.168.101.75:80/conexion_android/modificar_categoria.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Registro modificado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al modificar el registro: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_categoria", String.valueOf(item.getId_categoria()));
                params.put("categorias", item.getCategorias());
                params.put("identifi_admin", String.valueOf(item.getIdentifi_admin()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void deleteItem(Items_Categorias item) {
        String url = "http://192.168.101.75:80/conexion_android/eliminar_categoria.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al eliminar el registro: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_categoria", String.valueOf(item.getId_categoria()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


}
