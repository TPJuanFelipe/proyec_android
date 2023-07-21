package com.proyect.inicioSesion_y_registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.proyect.actividad.vista_actividad;
import com.proyect.categoria.menu_categoria;
import com.proyect.categoria.menu_frag_Catego;
import com.proyect.inven_sena.R;

public class menu_inv extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_menu_inv );
    }
    public void ir_menu_categoria( View view ){
        Intent ir_menu = new Intent (menu_inv.this, menu_categoria.class );
        startActivity ( ir_menu );
    }

    public void ir_vista_actividad(View view){
        Intent ir_menu = new Intent (menu_inv.this, vista_actividad.class );
        startActivity ( ir_menu );
    }
}