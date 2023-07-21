package com.proyect.vista_cate_y_herra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.proyect.categoria.categoria_item;
import com.proyect.herramienta.herramienta_item;
import com.proyect.inven_sena.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class vista_catego_y_herra extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<herramienta_item> listar_herra;
    private List<categoria_item> listar_catego;
    private adaptador_recycler_cateYherra adaptador;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_vista_catego_y_herra );
        recyclerView = findViewById ( R.id.recycler_herra_y_catego );
        LinearLayoutManager layoutManager = new LinearLayoutManager (  this);
        recyclerView.setLayoutManager ( layoutManager );
        listar_herra = new ArrayList<> ();
        listar_catego = new ArrayList<> ();
        adaptador = new adaptador_recycler_cateYherra (listar_herra, listar_catego );
        adaptador.setOnItemClickListener ( new adaptador_recycler_cateYherra.OnItemClickListener () {
            @Override
            public void onItemClick ( herramienta_item item ) {

            }

            @Override
            public void onItemClick ( categoria_item item ) {

            }

        } );

        recyclerView.setAdapter ( adaptador );
        obtenerlista_herra_atualizada ();
        obtenerlista_catego_atualizada ();


    }

    public void obtenerlista_herra_atualizada(){
        mostrar_herra_recycler (getApplicationContext (), new Response.Listener<List<herramienta_item>>() {
            @Override
            public void onResponse(List<herramienta_item> response) {
                listar_herra.clear();
                listar_herra.addAll(response);
                adaptador.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(vista_catego_y_herra.this, "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerlista_catego_atualizada(){
        mostrar_cate_recycler (getApplicationContext (), new Response.Listener<List<categoria_item>>() {
            @Override
            public void onResponse(List<categoria_item> response) {
                listar_catego.clear();
                listar_catego.addAll(response);
                adaptador.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(vista_catego_y_herra.this, "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void mostrar_herra_recycler( Context context, Response.Listener<List<herramienta_item>> successListener, Response.ErrorListener errorListener) {
        String url = "http://192.168.43.79:80/conexion_android_herramienta/consultar_Herramientas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<herramienta_item> itemList = new ArrayList<> ();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id_productos = jsonObject.getInt("id_productos");
                                String nombre = jsonObject.getString("nombre");
                                String descripcion = jsonObject.getString ( "descripcion" );
                                int cantidad = jsonObject.getInt("cantidad");
                                String imagen = jsonObject.getString ( "imagen" );
                                int  id_categoria = jsonObject.getInt ( "id_categoria" );



                                herramienta_item item = new herramienta_item ();
                                item.setId_herra (id_productos);
                                item.setNombre (nombre);
                                item.setDescripcion (descripcion);
                                item.setCantidad ( cantidad );
                                item.setImagen ( imagen );
                                item.setId_categoria ( id_categoria );

                                itemList.add(item);
                            }
                            successListener.onResponse(itemList);
                        } catch (JSONException e) {
                            errorListener.onErrorResponse(new VolleyError ("Error al analizar la respuesta JSON"));
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

    public void mostrar_cate_recycler( Context context, Response.Listener<List<categoria_item>> successListener, Response.ErrorListener errorListener) {
        String url = "http://192.168.43.79:80/conexion_android_categoria/consultar_categoria.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<categoria_item> itemList = new ArrayList<> ();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id_categoria = jsonObject.getInt("id_categoria");
                                String categorias = jsonObject.getString ("categorias");




                                categoria_item item_cate = new categoria_item ();
                                item_cate.setId_categoria (id_categoria);
                                item_cate.setCategorias (categorias);



                                itemList.add(item_cate);
                            }
                            successListener.onResponse(itemList);
                        } catch (JSONException e) {
                            errorListener.onErrorResponse(new VolleyError ("Error al analizar la respuesta JSON"));
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
}