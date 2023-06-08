package com.proyect.categorias;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Vista extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;
    private Db_Categorias databaseHelper;

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista);

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
        recyclerView.setAdapter(itemAdapter);

        @SuppressLint("MissingInflatedId") ImageView irButton = findViewById(R.id.ir_frag);
        irButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemFragment();
            }
        });

        databaseHelper = new Db_Categorias (this);
        itemList.addAll(databaseHelper.getAllItems());
        itemAdapter.notifyDataSetChanged();
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