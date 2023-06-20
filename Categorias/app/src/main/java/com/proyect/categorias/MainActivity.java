package com.proyect.categorias;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {
    private ImageView ir_frag_categoria;
    private Db_categorias db_categorias;
    private RecyclerView recyclerView;
    private List<Items_Categorias> listarItems;
    private adactador_recyclerview adapterItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ir_frag_categoria= findViewById ( R.id.ir_frag_categoria );

        db_categorias = new Db_categorias(this);
        recyclerView =findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        listarItems = new ArrayList<> ();
        adapterItems = new adactador_recyclerview(listarItems);
        adapterItems.setOnItemClickListener ( new adactador_recyclerview.OnItemClickListener () {
            @Override
            public void onItemClick ( Items_Categorias item ) {
                openItemFragment (item);
            }
        } );
        recyclerView.setAdapter(adapterItems);
        otenerlista_atualizada ();



        ir_frag_categoria.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                openItemFragment ();
            }
        } );


    }

    private void openItemFragment() {
        Fragment itemFragment = new crud_Categoria ();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte, itemFragment);
        fragmentTransaction.commit();
    }

    private void openItemFragment(Items_Categorias item) {
        Fragment itemFragment = crud_Categoria.newInstance(item);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte, itemFragment);
        fragmentTransaction.commit();
    }

    public void otenerlista_atualizada(){
        db_categorias.getAllItems(new Response.Listener<List<Items_Categorias>>() {
            @Override
            public void onResponse(List<Items_Categorias> response) {
                listarItems.clear();
                listarItems.addAll(response);
                adapterItems.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(MainActivity.this, "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
