package com.proyect.categoria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.proyect.actividad.frag_actividad_a_realizar;
import com.proyect.herramienta.crud_herramienta;
import com.proyect.inicioSesion_y_registro.menu_inv;
import com.proyect.inven_sena.R;
import com.proyect.vista_cate_y_herra.vista_catego_y_herra;


public class menu_frag_Catego extends Fragment {
    ImageView img_btn_salir_frag;
    ConstraintLayout ir_frag_crud_catego, regresar_al_menu, ir_vista_herra, ir_frag_crud_herra, ir_insert_actividad, ir_prestamo ;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view= inflater.inflate ( R.layout.fragment_menu_frag__catego, container, false );
        img_btn_salir_frag = view.findViewById ( R.id.img_salir_frag );
        ir_frag_crud_catego = view.findViewById ( R.id.cL_crud_frag_categoria );
        ir_frag_crud_herra = view.findViewById ( R.id.cL_crud_herra );
        ir_vista_herra = view.findViewById ( R.id.cL_vista_cateHerra );
        regresar_al_menu = view.findViewById ( R.id.cL_regresar_menu );
        ir_insert_actividad = view.findViewById ( R.id.cL_insert_actividad );

        img_btn_salir_frag.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent ir = new Intent (getActivity (), menu_categoria.class);
                startActivity ( ir );
            }
        } );

        ir_frag_crud_catego.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {

                ir_crud_categoria_desde_cate ( );

            }
        } );

        ir_frag_crud_herra.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                ir_crud_herra_desde_cate ();


            }
        } );

        ir_vista_herra.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                ir_vista_herra ();
            }
        } );
        regresar_al_menu.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Regresar_al_menu ();

            }
        } );

        ir_insert_actividad.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                ir_insert_actividad_desde_cate ();


            }
        } );





        return view;
    }

    public void  ir_crud_categoria_desde_cate(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte_cate_crud, new crud_categoria ());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }



    public void ir_crud_herra_desde_cate(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte_cate_crud, new crud_herramienta () );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void ir_vista_herra(){
        Intent ir_vista = new Intent (getActivity (), vista_catego_y_herra.class);
        startActivity ( ir_vista );
    }

    public void Regresar_al_menu(){
        Intent regresar_menu = new Intent (getActivity (), menu_inv.class);
        startActivity ( regresar_menu );
    }

    public void ir_insert_actividad_desde_cate(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte_cate_crud,   new frag_actividad_a_realizar () );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}