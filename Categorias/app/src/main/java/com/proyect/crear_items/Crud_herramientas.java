package com.proyect.crear_items;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.categorias.Db_Categorias;
import com.proyect.categorias.R;

import java.util.ArrayList;
import java.util.List;

public class Crud_herramientas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private Db_Categorias databaseHelper;
    private Button loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.crud_herramientas );



        databaseHelper = new Db_Categorias (this);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList);
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                openItemFragment(item);
            }
        });
        itemList.addAll(databaseHelper.getAllItems());
        itemAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(itemAdapter);

        ImageView Ir= findViewById ( R.id.ir_frag );

        Ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemFragment ();
            }
        });

    }

    private void openItemFragment() {
        ItemFragment itemFragment = new ItemFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte, itemFragment);
        fragmentTransaction.commit();
    }

    private void openItemFragment(Item item) {
        ItemFragment itemFragment = ItemFragment.newInstance(item);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conte, itemFragment);
        fragmentTransaction.commit();
    }
}




