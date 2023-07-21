package com.proyect.herramienta;

import android.content.Context;
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
import com.proyect.categoria.categoria_item;
import com.proyect.inven_sena.R;

import java.util.HashMap;
import java.util.Map;

public class crud_herramienta extends Fragment {

    EditText edt_id_herra_crud, edt_nombre_herra_crud, edt_descrip_herra_crud, edt_cantidad_herra_crud,
             edt_imagen_herra_crud, edt_idCatego_herra_crud, edt_codigoAdmin_herra_crud;
    ImageView img_insertar_herra, img_modifi_herra, img_eliminar_herra;



    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View v = inflater.inflate ( R.layout.fragment_crud_herramientas, container, false );
        edt_id_herra_crud = v.findViewById ( R.id.edt_id_herra_crud );
        edt_nombre_herra_crud = v.findViewById ( R.id.edt_nombre_herra_crud );
        edt_descrip_herra_crud = v.findViewById ( R.id.edt_descrip_herra_crud );
        edt_cantidad_herra_crud = v.findViewById ( R.id.edt_cantidad_herra_crud );
        edt_imagen_herra_crud = v.findViewById ( R.id.edt_imagen_herra_crud );
        edt_idCatego_herra_crud = v.findViewById ( R.id.edt_id_catego_herra_crud );
        edt_codigoAdmin_herra_crud = v.findViewById ( R.id.edt_codigo_herra_crud );
        img_insertar_herra = v.findViewById ( R.id.img_insertar_herra );
        img_modifi_herra = v.findViewById ( R.id.img_modifi_herra );
        img_eliminar_herra = v.findViewById (  R.id.img_eliminar_herra );
        img_insertar_herra.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String id_herra = edt_id_herra_crud.getText ().toString ();
                String nombre_herra = edt_nombre_herra_crud.getText ().toString ();
                String descrip_herra = edt_descrip_herra_crud.getText ().toString ();
                String cantidad_herra = edt_cantidad_herra_crud.getText ().toString ();
                String image_herra = edt_imagen_herra_crud.getText ().toString ();
                String id_catego = edt_idCatego_herra_crud.getText ().toString ();
                String codigo_admin = edt_codigoAdmin_herra_crud.getText ().toString ();
                
                if(!id_herra.isEmpty () && !nombre_herra.isEmpty () && !descrip_herra.isEmpty () && !cantidad_herra.isEmpty () &&
                   !cantidad_herra.isEmpty () && !image_herra.isEmpty () && !id_catego.isEmpty () && !codigo_admin.isEmpty () ) {

                    herramienta_item insert_herra = new herramienta_item ();
                    insert_herra.setId_herra ( Integer.parseInt ( id_herra ) );
                    insert_herra.setNombre ( nombre_herra );
                    insert_herra.setDescripcion ( descrip_herra );
                    insert_herra.setCantidad ( Integer.parseInt ( cantidad_herra ) );
                    insert_herra.setImagen ( image_herra );
                    insert_herra.setId_categoria ( Integer.parseInt ( id_catego ) );
                    insert_herra.setCodigo_admin ( Integer.parseInt ( codigo_admin ) );
                    insert_herra_server ( getContext (), insert_herra );
                }else{
                    Toast.makeText ( getContext (), "Por favor llenar todos los campos ", Toast.LENGTH_SHORT ).show ();
                }

;            }
        } );
        
        img_modifi_herra.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String id_herra = edt_id_herra_crud.getText ().toString ();
                String nombre_herra = edt_nombre_herra_crud.getText ().toString ();
                String descrip_herra = edt_descrip_herra_crud.getText ().toString ();
                String cantidad_herra = edt_cantidad_herra_crud.getText ().toString ();
                String image_herra = edt_imagen_herra_crud.getText ().toString ();
                String id_catego = edt_idCatego_herra_crud.getText ().toString ();
                String codigo_admin = edt_codigoAdmin_herra_crud.getText ().toString ();

                if(!id_herra.isEmpty () && !nombre_herra.isEmpty () && !descrip_herra.isEmpty () && !cantidad_herra.isEmpty () &&
                        !cantidad_herra.isEmpty () && !image_herra.isEmpty () && !id_catego.isEmpty () && !codigo_admin.isEmpty () ) {

                    herramienta_item modifi_herra = new herramienta_item ();
                    modifi_herra.setId_herra ( Integer.parseInt ( id_herra ) );
                    modifi_herra.setNombre ( nombre_herra );
                    modifi_herra.setDescripcion ( descrip_herra );
                    modifi_herra.setCantidad ( Integer.parseInt ( cantidad_herra ) );
                    modifi_herra.setImagen ( image_herra );
                    modifi_herra.setId_categoria ( Integer.parseInt ( id_catego ) );
                    modifi_herra.setCodigo_admin ( Integer.parseInt ( codigo_admin ) );
                    modificar_herra_server ( getContext (), modifi_herra );
                }else{
                    Toast.makeText ( getContext (), "Por favor llenar todos los campos ", Toast.LENGTH_SHORT ).show ();
                }
                
            }
        } );
        
        
        img_eliminar_herra.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String id_herra = edt_id_herra_crud.getText ().toString ();
                if(!id_herra.isEmpty () ) {

                    herramienta_item eliminar_herra = new herramienta_item ();
                    eliminar_herra.setId_herra ( Integer.parseInt ( id_herra ) );
                    eliminar_herra_server ( getContext (), eliminar_herra );

                }else{
                    Toast.makeText ( getContext (), "Por favor llenar todos los campos ", Toast.LENGTH_SHORT ).show ();
                }
                
            }
        } );


        return v;
    }

    public void insert_herra_server( Context context, herramienta_item item) {
        String url = "http://192.168.43.79:80/conexion_android_herramienta/insertar_Herramientas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Herramienta insertado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al insertar la herramienta " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put("id_productos", String.valueOf(item.getId_herra ()));
                params.put("nombre", item.getNombre ());
                params.put ( "descripcion", item.getDescripcion () );
                params.put ( "cantidad", String.valueOf ( item.getCantidad ()));
                params.put ( "imagen",  item.getImagen () );
                params.put ( "id_categoria", String.valueOf ( item.getId_categoria () ));
                params.put ( "identifi_admin",String.valueOf ( item.getCodigo_admin () ) );


                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    public void modificar_herra_server( Context context, herramienta_item item) {
        String url = "http://192.168.43.79:80/conexion_android_herramienta/modificar_Herramientas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Herramienta modificada correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al modificar la herramienta " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put("id_productos", String.valueOf(item.getId_herra ()));
                params.put("nombre", item.getNombre ());
                params.put ( "descripcion", item.getDescripcion () );
                params.put ( "cantidad", String.valueOf ( item.getCantidad ()));
                params.put ( "imagen",  item.getImagen () );
                params.put ( "id_categoria", String.valueOf ( item.getId_categoria () ));
                params.put ( "identifi_admin",String.valueOf ( item.getCodigo_admin () ) );


                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void eliminar_herra_server( Context context, herramienta_item item) {
        String url = "http://192.168.43.79:80/conexion_android_herramienta/eliminar_Herramientas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Herramienta eliminada correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al eliminar la herramienta " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put("id_productos", String.valueOf(item.getId_herra ()));
               

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}