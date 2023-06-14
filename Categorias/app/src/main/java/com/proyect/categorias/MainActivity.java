package com.proyect.categorias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.proyect.crear_items.Crud_herramientas;

public class MainActivity extends AppCompatActivity {


    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        ImageView ir_Catego = findViewById(R.id.imv_IrCategoria);
        ir_Catego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(MainActivity.this, Vista_categorias.class);
                startActivity(ir);
            }
        });

         ImageView ir_Herra = findViewById(R.id.imv_IrHerramientas);
        ir_Herra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(MainActivity.this, Crud_herramientas.class);
                startActivity(ir);
            }
        });
    }
}