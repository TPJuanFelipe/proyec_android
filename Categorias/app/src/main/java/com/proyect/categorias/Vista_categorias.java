package com.proyect.categorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Vista_categorias extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Item_Adtdr item_Adtdr;
    private long lastClickTime = 0;
    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // Intervalo de tiempo máximo entre dos clics para considerarlo como doble clic

    private List<Items_Categorias> ListItem;
    private Db_Categorias db_categorias;
    Button crud_frag;

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_vista_categorias );
        crud_frag = findViewById (  R.id.btnCatego_crud );
        recyclerView= findViewById ( R.id.categoria_recyclerview );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        ListItem= new ArrayList<> ();
        item_Adtdr = new Item_Adtdr ( ListItem );
        item_Adtdr.setItemClickListener ( new Item_Adtdr.ItemClicklist () {
            @Override
            public void onItemClick ( Items_Categorias item ) {
                { openItemFragment (item);

            }
        }
        });
        recyclerView.setAdapter ( item_Adtdr );

        db_categorias = new Db_Categorias (this);
        ListItem.addAll(db_categorias.getAllItem());
        item_Adtdr.notifyDataSetChanged();


        crud_frag.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                openItemFragment ();
            }
        } );


    }



    private void openItemFragment() {
        Fragment itemFragment = new frag_crud_Categoria();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Categoria_conte, itemFragment);
        fragmentTransaction.commit();

    }


    private void openItemFragment(Items_Categorias item) {
        Fragment itemFragment = frag_crud_Categoria.newInstance(item);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Categoria_conte, itemFragment);
        fragmentTransaction.commit();


        item_Adtdr.setItemClickListener(new Item_Adtdr.ItemClicklist() {
            @Override
            public void onItemClick(Items_Categorias item) {
                // Verificar si se ha realizado un doble clic
                if (System.currentTimeMillis() - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                    // Se ha realizado un doble clic, crear y abrir la nueva actividad
                    crearNuevaActividad(item);
                }
                lastClickTime = System.currentTimeMillis();
            }
        });
    }



    private void crearNuevaActividad(Items_Categorias item) {
        // Obtener el nombre de la categoría del item
        String nombreCategoria = item.getNombre();

        // Crear un nuevo Intent para la nueva actividad
        Intent intent = new Intent(Vista_categorias.this, NuevaActividad.class);

        // Agregar información adicional al Intent si es necesario
        intent.putExtra("nombre_categoria", nombreCategoria);


        // Iniciar la nueva actividad
        startActivity(intent);
    }





}