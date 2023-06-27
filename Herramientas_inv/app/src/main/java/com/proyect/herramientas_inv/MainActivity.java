package com.proyect.herramientas_inv;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

Button ir_frag_herra;
List<Item_Herramienta> listar_item_herra;
RecyclerView recyclerView;
Db_Herra db_herra;
adactador_Herra adtd_herra;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ir_frag_herra= findViewById ( R.id.ir_frag_herra );
        db_herra = new Db_Herra (this);
        recyclerView =findViewById(R.id.recycler_herra);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        listar_item_herra = new ArrayList<> ();
        adtd_herra = new adactador_Herra (listar_item_herra);
        adtd_herra.setOnItemClickListener ( new adactador_Herra.OnItemClickListener () {
            @Override
            public void onItemClick ( Item_Herramienta item ) {
                Fragmento_ir_items_click ( item );
            }
        } );
        recyclerView.setAdapter(adtd_herra);
        obtener_lista_actualizada ();

        ir_frag_herra.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Fragmento_ir_click ();
            }
        } );



    }

    private void Fragmento_ir_click() {
        Fragment itemFragment = new crud_Herramientas ();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte_herra, itemFragment);
        fragmentTransaction.commit();
    }


    private void Fragmento_ir_items_click(Item_Herramienta item){
        Fragment frag_item = crud_Herramientas.nueva_instancia ( item );
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte_herra, frag_item);
        fragmentTransaction.commit();
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

                if (adtd_herra != null){
                    adtd_herra.notifyDataSetChanged ();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                Toast.makeText(MainActivity.this, "Error al obtener los elementos: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}