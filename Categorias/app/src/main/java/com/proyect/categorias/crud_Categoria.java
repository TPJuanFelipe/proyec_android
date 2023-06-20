package com.proyect.categorias;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;


public class crud_Categoria extends Fragment {






    private EditText idCategoriaEditText;
    private EditText categoriasEditText;
    private EditText identifiAdminEditText;
    private ImageView insertButton;
    private ImageView modifibtn;
    private ImageView elimibtn;
    private Db_categorias db_categorias;
    private RecyclerView recyclerView;
    private List<Items_Categorias> listarItems;
    private adactador_recyclerview adapterItems;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view=inflater.inflate ( R.layout.fragment_crud__categoria, container, false );

        idCategoriaEditText = view.findViewById(R.id.edt_id_categoria);
        categoriasEditText = view.findViewById(R.id.edt_categorias);
        identifiAdminEditText = view.findViewById(R.id.edt_identifi_admin);
        insertButton = view.findViewById(R.id.iv_insertar_categoria);
        modifibtn= view.findViewById ( R.id.iv_modificar_categoria );
        elimibtn= view.findViewById ( R.id.iv_eliminar_categoria );
        db_categorias = new Db_categorias(getContext ());







        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items_Categorias newItem = new Items_Categorias();
                newItem.setId_categoria(Integer.parseInt(idCategoriaEditText.getText().toString()));
                newItem.setCategorias(categoriasEditText.getText().toString());
                newItem.setIdentifi_admin(Integer.parseInt(identifiAdminEditText.getText().toString()));
                db_categorias.insertItem(newItem);
                otenerlista_atualizada ();

                }
        });




        modifibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Items_Categorias modi_item = new Items_Categorias();
                modi_item.setId_categoria(Integer.parseInt(idCategoriaEditText.getText().toString()));
                modi_item.setCategorias(categoriasEditText.getText().toString());
                modi_item.setIdentifi_admin(Integer.parseInt(identifiAdminEditText.getText().toString()));
                db_categorias.updateItem(modi_item);
                otenerlista_atualizada ();

            }
        });


        elimibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Items_Categorias modi_item = new Items_Categorias();
                modi_item.setId_categoria(Integer.parseInt(idCategoriaEditText.getText().toString()));
                modi_item.setCategorias(categoriasEditText.getText().toString());
                modi_item.setIdentifi_admin(Integer.parseInt(identifiAdminEditText.getText().toString()));
                db_categorias.deleteItem (modi_item);
                otenerlista_atualizada ();


            }


        });





        return view;
    }

    public void otenerlista_atualizada(){
        db_categorias.getAllItems(new Response.Listener<List<Items_Categorias>>() {
            @Override
            public void onResponse(List<Items_Categorias> response) {
                if (listarItems != null) {
                    listarItems.clear();
                }

                if (listarItems != null) {
                    listarItems.addAll(response);
                }

                if (adapterItems != null){
                    adapterItems.notifyDataSetChanged ();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(getActivity (), "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static crud_Categoria newInstance(Items_Categorias item) {
        Fragment fragment = new crud_Categoria ();
        Bundle args = new Bundle();
        args.putParcelable("item", (Parcelable) item );
        fragment.setArguments(args);
        return (crud_Categoria) fragment;
    }



}