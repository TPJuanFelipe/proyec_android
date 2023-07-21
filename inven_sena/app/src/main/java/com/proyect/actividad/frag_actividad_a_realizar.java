package com.proyect.actividad;

import android.annotation.SuppressLint;
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


public class frag_actividad_a_realizar extends Fragment {

    EditText edt_id_actividad, edt_trabajo_reali, edt_fecha_reali, edt_id_trabajador;
    ImageView img_insert_Activi;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_frag_actividad_a_realizar, container, false );
        edt_id_actividad = view.findViewById ( R.id.edt_id_actividad_activi );
        edt_id_trabajador = view.findViewById ( R.id.edt_id_trabajador_activi );
        edt_fecha_reali = view.findViewById ( R.id.edt_fecha_trabajado_activ );
        edt_trabajo_reali = view.findViewById ( R.id.edt_trabajo_reali_activi );
        img_insert_Activi = view.findViewById ( R.id.img_insert_actividad );
        img_insert_Activi.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                String id_actividad = edt_id_actividad.getText ().toString ();
                String trabajo_reali= edt_trabajo_reali.getText().toString ();
                String fecha = edt_fecha_reali.getText ().toString ();
                String id_trabajador = edt_id_trabajador.getText ().toString ();

                actividad_item insert_activi = new actividad_item ();
                insert_activi.setId_trabajo (  Integer.parseInt (id_actividad));
                insert_activi.setTrabajo_realizado ( trabajo_reali );
                insert_activi.setFecha_realizado ( fecha );
                insert_activi.setId_trabajador ( Integer.parseInt (id_trabajador) );
                insert_actividad ( getContext (), insert_activi );


            }
        } );
        return view;
    }


    public void insert_actividad( Context context, actividad_item item) {
        String url = "http://192.168.43.79:80/conexion_android_actividad/insertar_actividad.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Actividad insertada correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error) {
                        Toast.makeText(context, "Error al insertar el actividad " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put("id_trabajo", String.valueOf(item.getId_trabajo ()));
                params.put("trabajoRealizado", item.getTrabajo_realizado ());
                params.put("fechaRealizado", item.getFecha_realizado ());
                params.put("id_trabajador", String.valueOf(item.getId_trabajador ()));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}