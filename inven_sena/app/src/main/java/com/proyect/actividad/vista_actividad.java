package com.proyect.actividad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyect.categoria.categoria_item;
import com.proyect.categoria.menu_frag_Catego;
import com.proyect.herramienta.herramienta_item;
import com.proyect.inicioSesion_y_registro.login;
import com.proyect.inven_sena.R;
import com.proyect.vista_cate_y_herra.vista_catego_y_herra;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class vista_actividad extends AppCompatActivity {
    FloatingActionButton btn_fla_abrir_menu;
    private RecyclerView recyclerView;
    private List<actividad_item> listar_activi;
    private List<login> listar_tbjd;
    private adaptador_recycler_activi adapter_activi;
    private RequestQueue requestQueue; // Instancia global de RequestQueue

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_actividad);
        btn_fla_abrir_menu = findViewById(R.id.fab_btn_menu_actividad);
        recyclerView = findViewById(R.id.recycler_vista_activi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listar_activi = new ArrayList<>();
        listar_tbjd = new ArrayList<>();
        adapter_activi = new adaptador_recycler_activi(listar_activi, listar_tbjd);
        recyclerView.setAdapter(adapter_activi);

        // Inicializar la instancia de RequestQueue
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        obtenerlista_activi_atualizada();
        obtenerlista_trabajador_atualizada();

        btn_fla_abrir_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrir_menu_activi();
            }
        });
    }

    public void abrir_menu_activi() {
        Fragment frag = new menu_frag_Catego();
        FragmentManager fraM = getSupportFragmentManager();
        FragmentTransaction frat = fraM.beginTransaction();
        frat.replace(R.id.conte_cate_crud, frag);
        frat.commit();
    }

    public void obtenerlista_activi_atualizada(){
        mostrar_actividad_recycler  (getApplicationContext (), new Response.Listener<List<actividad_item>>() {
            @Override
            public void onResponse(List<actividad_item> response) {
                listar_activi.clear();
                listar_activi.addAll(response);
                adapter_activi.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(vista_actividad.this, "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerlista_trabajador_atualizada(){
        mostrar_trabajador_recycler  (getApplicationContext (), new Response.Listener<List<login>>() {
            @Override
            public void onResponse(List<login> response) {
                listar_tbjd.clear();
                listar_tbjd.addAll(response);
                adapter_activi.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(vista_actividad.this, "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void mostrar_actividad_recycler( Context context, Response.Listener<List<actividad_item>> successListener, Response.ErrorListener errorListener) {
        String url = "http://192.168.43.79:80/conexion_android_actividad/consultar_actividad.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<actividad_item> itemList = new ArrayList<> ();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id_trabajo = jsonObject.getInt("id_trabajo");
                                String trabajoRealizado = jsonObject.getString("trabajoRealizado");
                                String fechaRealizado = jsonObject.getString ( "fechaRealizado" );
                                int id_trabajador = jsonObject.getInt("id_trabajador");



                                actividad_item item = new actividad_item ();
                                item.setId_trabajo (id_trabajo);
                                item.setTrabajo_realizado (trabajoRealizado);
                                item.setFecha_realizado (fechaRealizado);
                                item.setId_trabajador ( id_trabajador );

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

    public void mostrar_trabajador_recycler( Context context, Response.Listener<List<login>> successListener, Response.ErrorListener errorListener) {
        String url = "http://192.168.43.79:80/conexion_android_actividad/consultar_trabajador.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest( Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<login> itemList = new ArrayList<> ();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int identificacion = jsonObject.getInt("identificacion");
                                String tipo_identificacion = jsonObject.getString ( "tipo_identificacion" );
                                String nombre_completo = jsonObject.getString ("nombre_completo");
                                String correo = jsonObject.getString ( "correo" );
                                String usuario = jsonObject.getString ( "usuario" );
                                String contrasena = jsonObject.getString ( "contrasena" );





                                login item_login = new login ();
                                item_login.setId_identi_tbj (identificacion);
                                item_login.setNombre_tbj (nombre_completo);
                                item_login.setTipo_docu_tbj ( tipo_identificacion );
                                item_login.setCorreo_tbj ( correo );
                                item_login.setUsu_tbj ( usuario );
                                item_login.setContra_tbj ( contrasena );



                                itemList.add(item_login);
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
