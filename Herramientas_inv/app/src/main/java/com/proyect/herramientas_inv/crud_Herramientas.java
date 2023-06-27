package com.proyect.herramientas_inv;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;


public class crud_Herramientas extends Fragment {


    private EditText edt_id_producto;
    private EditText edt_nombre;
    private EditText edt_descripcion;
    private EditText edt_cantidad;
    private EditText edt_imagen;
    private EditText edt_id_categoria;
    private EditText edt_identifi_admin;
    private ImageView btn_insertar_Herra;
    private ImageView btn_modificar_Herra;
    private ImageView btn_eliminar_Herra;
    private List<Item_Herramienta> listar_item_herra;
    private Db_Herra db_herra;
    private adactador_Herra adp_herra;






    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view=inflater.inflate ( R.layout.fragment_crud__herramientas, container, false );
        db_herra = new Db_Herra ( getContext () );

        edt_id_producto= view.findViewById ( R.id.edt_id_producto_herra );
        edt_nombre= view.findViewById ( R.id.edt_nombre_herra );
        edt_descripcion = view.findViewById ( R.id.edt_descrip_herra);
        edt_cantidad = view.findViewById ( R.id.edt_cantidad_herra );
        edt_imagen= view.findViewById ( R.id.edt_imagen_herra );
        edt_id_categoria= view.findViewById ( R.id.edt_id_categoria_herra );
        edt_identifi_admin= view.findViewById ( R.id.edt_codigo_admin );
        btn_insertar_Herra = view.findViewById(R.id.btn_insertar_Herra);
        btn_modificar_Herra = view.findViewById ( R.id.btn_modifi_Herra );
        btn_eliminar_Herra = view.findViewById (  R.id.btn_eliminar_Herra );




        btn_insertar_Herra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item_Herramienta newItem = new Item_Herramienta (  );
                newItem.setId_Herra (Integer.parseInt(edt_id_producto.getText().toString()));
                newItem.setNombre_Herra (edt_nombre.getText().toString());
                newItem.setDescripcion_Herra (edt_descripcion.getText ().toString ());
                newItem.setCantidad_Herra (Integer.parseInt ( edt_cantidad.getText ().toString () ));
                newItem.setImagen_Herra  ( edt_imagen.getText ().toString ()  );
                newItem.setId_cate (Integer.parseInt ( edt_id_categoria.getText ().toString () ));
                newItem.setCodigo_admin ( Integer.parseInt ( edt_identifi_admin.getText ().toString () ) );
                db_herra.insert_Item_Herra (newItem);
                obtener_lista_actualizada ();


            }
        });

        btn_modificar_Herra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item_Herramienta modifi_item = new Item_Herramienta (  );
                modifi_item.setId_Herra (Integer.parseInt(edt_id_producto.getText().toString()));
                modifi_item.setNombre_Herra (edt_nombre.getText().toString());
                modifi_item.setDescripcion_Herra (edt_descripcion.getText ().toString ());
                modifi_item.setCantidad_Herra (Integer.parseInt ( edt_cantidad.getText ().toString () ));
                modifi_item.setImagen_Herra  ( edt_imagen.getText ().toString ()  );
                modifi_item.setCodigo_admin ( Integer.parseInt ( edt_identifi_admin.getText ().toString () ) );
                db_herra.modifi_Item_Herra (modifi_item);
                obtener_lista_actualizada ();


            }
        });



        return view;
    }

    public  void obtener_lista_actualizada(){
        db_herra.getAllItems(new Response.Listener<List<Item_Herramienta>>() {
            @Override
            public void onResponse(List<Item_Herramienta> response) {
                if (listar_item_herra != null) {
                    listar_item_herra.clear();
                }

                if (listar_item_herra != null) {
                    listar_item_herra.addAll(response);
                }

                if (adp_herra != null){
                    adp_herra.notifyDataSetChanged ();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(getActivity (), "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static crud_Herramientas nueva_instancia(Item_Herramienta item) {
        Fragment frag_item = new crud_Herramientas ();
        Bundle agrg=new Bundle ();
        agrg.putParcelable ( "item",(Parcelable) item);
        frag_item.setArguments ( agrg );
        return (crud_Herramientas) frag_item;
    }

}