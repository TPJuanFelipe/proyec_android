package com.proyect.categoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyect.inven_sena.R;

public class menu_categoria extends AppCompatActivity {
    FloatingActionButton btn_ir_frag_categoria;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_menu_categoria );
        btn_ir_frag_categoria= findViewById ( R.id.fab_btn_menu_actividad );
        btn_ir_frag_categoria.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                abrir_menu_Cate ();

            }

        } );
    }

    public void abrir_menu_Cate(){
        Fragment frag= new menu_frag_Catego ();
        FragmentManager fraM = getSupportFragmentManager ();
        FragmentTransaction frat= fraM.beginTransaction ();
        frat.replace ( R.id.conte_cate_crud, frag );
        frat.commit ();
    }
}