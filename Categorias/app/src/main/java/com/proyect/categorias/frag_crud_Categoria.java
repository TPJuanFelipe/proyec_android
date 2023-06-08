package com.proyect.categorias;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class frag_crud_Categoria extends Fragment {
    private EditText edt_categoria;
    ImageView insert_categoria;
    ImageView modifi_categoria;
    ImageView elimi_categoria;
    Items_Categorias currentItem;
    Db_Categorias db_categorias;

String crearNuevasActividades;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_frag_crud__categoria, container, false );
        edt_categoria = view.findViewById ( R.id.nombre_categoria );
        insert_categoria = view.findViewById ( R.id.btn_insert_categoria );
        modifi_categoria = view.findViewById ( R.id.btn_modifi_categoria );
        elimi_categoria = view.findViewById ( R.id.btn_elimi_categoria );
        db_categorias = new Db_Categorias ( requireContext () );


        insert_categoria.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {

                agregar_item_categoria ();

            }
        } );

        modifi_categoria.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                modifi_item_categoria ();

            }
        } );

        elimi_categoria.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                eliminar_item_categoria ();

            }
        } );




        return  view;
    }

    public static  frag_crud_Categoria newInstance(Items_Categorias item){
        frag_crud_Categoria fragment = new frag_crud_Categoria ();
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            currentItem = getArguments().getParcelable("item");
            if (currentItem != null) {
                edt_categoria.setText(currentItem.getNombre());
                modifi_categoria.setVisibility(View.VISIBLE);
                elimi_categoria.setVisibility(View.VISIBLE);
            }
        }
    }

    private void agregar_item_categoria() {
        String nombre = edt_categoria.getText().toString().trim();


        Items_Categorias newItem = new Items_Categorias ();
        newItem.setNombre ( nombre );
        Toast.makeText(requireContext(), "Item agregado", Toast.LENGTH_SHORT).show();
        db_categorias.insertItem ( newItem );
        edt_categoria.setText ( "" );
        crearNuevaActividad(newItem);

    }


    private void modifi_item_categoria(){
        if(currentItem != null);{
            String nombre = edt_categoria.getText().toString ().trim ();
            currentItem.setNombre (nombre);
            db_categorias.updateItem ( currentItem );
            Toast.makeText(requireContext(), "Categoria modificado", Toast.LENGTH_SHORT).show();

        }


    }

    private  void eliminar_item_categoria()
    {
        if (currentItem != null){
         db_categorias.deleteItem ( currentItem );
            Toast.makeText(requireContext(), "Categoria eliminado", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        }
    }
    private void crearNuevaActividad(Items_Categorias item) {
        // Obtener el nombre de la categoría del item
        String nombreCategoria = item.getNombre();

        // Crear un nuevo Intent para la nueva actividad
        Intent intent = new Intent(getActivity (), NuevaActividad.class);

        // Agregar información adicional al Intent si es necesario
        intent.putExtra("nombre_categoria", nombreCategoria);

        // Iniciar la nueva actividad
        startActivity(intent);
    }




}