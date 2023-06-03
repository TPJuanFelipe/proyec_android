package com.proyect.crear_items;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Activity_Elementos extends AppCompatActivity {
    private Db_IngresarItems databaseHelper;
    Button ir;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementos);

        ir = findViewById(R.id.ir);
        ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(Activity_Elementos.this, Vista.class);
                startActivity(ir);
            }
        });

        databaseHelper = new Db_IngresarItems(this);



        // Eliminar un item existente si itemList tiene al menos dos elementos


        // Obtener y mostrar todos los items
        List<Item> itemList = databaseHelper.getAllItems();
        for (Item item : itemList) {
            Log.d("Item", "Nombre: " + item.getNombre() + ", Cantidad: " + item.getCantidad() + ", Agotado: " + item.isAgotado());
        }
        if (itemList.size() > 0) {
            Item itemToUpdate = itemList.get(0);
            itemToUpdate.setCantidad(5);
            itemToUpdate.setAgotado(true);
            databaseHelper.updateItem(itemToUpdate);
        }
        // Modificar un item existente si itemList tiene al menos un elemento
        if (itemList.size() > 1) {
            Item itemToDelete = itemList.get(1);
            databaseHelper.deleteItem(itemToDelete);
        }
    }
}