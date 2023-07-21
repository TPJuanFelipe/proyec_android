package com.proyect.categoria;

import android.annotation.SuppressLint;
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
import com.proyect.inicioSesion_y_registro.login;
import com.proyect.inven_sena.R;

import java.util.HashMap;
import java.util.Map;


public class crud_categoria extends Fragment {
    EditText edt_nombre_categoria, edt_id_categoria, edt_codigo_admin;
    ImageView img_insertar_cate, img_modifi_cate, img_eliminar_cate, img_regresarcrudcate_menu;




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_crud_categoria, container, false );
        edt_nombre_categoria = view.findViewById ( R.id.edt_nombre_cate_crud );
        edt_id_categoria = view.findViewById ( R.id.edt_id_categoria_crud );
        edt_codigo_admin = view.findViewById ( R.id.edt_codigo_admin_crud );
        img_insertar_cate = view.findViewById ( R.id.img_btn_insertar_cate_crud );
        img_modifi_cate = view.findViewById ( R.id.img_btn_modifi_cate_crud );
        img_eliminar_cate = view.findViewById ( R.id.img_btn_eliminar_cate_crud );
        img_regresarcrudcate_menu = view.findViewById ( R.id.img_regresardeCrudCate_menu );
        img_insertar_cate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String nombre_categoria = edt_nombre_categoria.getText ().toString ();
                String id_categoria = edt_id_categoria.getText ().toString ();
                String codigo_admin = edt_codigo_admin.getText ().toString ();
                if(!nombre_categoria.isEmpty () && !id_categoria.isEmpty () && !codigo_admin.isEmpty () ){
                categoria_item insert_cate = new categoria_item (  );
                insert_cate.setCategorias ( nombre_categoria );
                insert_cate.setId_categoria ( Integer.parseInt (id_categoria) );
                insert_cate.setCodigo_admin ( Integer.parseInt (codigo_admin) );
                insertItem ( getContext (), insert_cate );
                }else{
                    Toast.makeText ( getContext (), "Por favor llene todos los campos ", Toast.LENGTH_SHORT ).show ();
                }

            }
        } );
        img_modifi_cate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String nombre_categoria = edt_nombre_categoria.getText ().toString ();
                String id_categoria = edt_id_categoria.getText ().toString ();
                String codigo_admin = edt_codigo_admin.getText ().toString ();

                if(!nombre_categoria.isEmpty () && !id_categoria.isEmpty () && !codigo_admin.isEmpty ()){
                    categoria_item modifi_catego= new categoria_item ();
                    modifi_catego.setId_categoria ( Integer.parseInt (id_categoria) );
                    modifi_catego.setCategorias ( nombre_categoria );
                    modifi_catego.setCodigo_admin ( Integer.parseInt (codigo_admin) );
                    modifi_catego ( getContext (), modifi_catego );

                }else{
                    Toast.makeText ( getContext (), "Por favor llenar ", Toast.LENGTH_SHORT ).show ();
                }
            }
        } );

        img_eliminar_cate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {

                String id_categoria = edt_id_categoria.getText ().toString ();

                if(!id_categoria.isEmpty ()){
                    categoria_item eliminar_cate = new categoria_item ();
                    eliminar_cate.setId_categoria ( Integer.parseInt (id_categoria) );
                    eliminar_cate ( getContext (), eliminar_cate );

                }else{
                    Toast.makeText ( getContext (), "por favor ingrese el id de la categoria ", Toast.LENGTH_SHORT ).show ();
                }

            }
        } );

        img_regresarcrudcate_menu.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent regre_crud_cate = new Intent (getActivity (), menu_categoria.class);
                startActivity ( regre_crud_cate );
            }
        } );


        return view;
    }

    public void insertItem( Context context, categoria_item item) {
        String url = "http://192.168.43.79:80/conexion_android_categoria/insertar_Categorias.php";
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
                params.put("id_categoria", String.valueOf(item.getId_categoria ()));
                params.put("categorias", item.getCategorias ());
                params.put("identifi_admin", String.valueOf(item.getCodigo_admin ()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void modifi_catego( Context context, categoria_item item) {
        String url = "http://192.168.43.79:80/conexion_android_categoria/modificar_categoria.php";
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
                params.put("id_categoria", String.valueOf(item.getId_categoria ()));
                params.put("categorias", item.getCategorias ());
                params.put("identifi_admin", String.valueOf(item.getCodigo_admin ()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void eliminar_cate( Context context, categoria_item item) {
        String url = "http://192.168.43.79:80/conexion_android_categoria/eliminar_categoria.php";
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
                params.put ( "id_categoria", String.valueOf ( item.getId_categoria () ) );

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}