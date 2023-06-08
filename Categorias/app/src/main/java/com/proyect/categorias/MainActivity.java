package com.proyect.categorias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

     ImageView ir;
     ImageView btn_ir_itemHerra;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
         ir = findViewById(R.id.imv_IrCategoria);
         btn_ir_itemHerra= findViewById ( R.id.Ir_btn_itemHerra );
        ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(MainActivity.this, Vista_categorias.class);
                startActivity(ir);
            }
        });


        btn_ir_itemHerra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(MainActivity.this, Vista.class);
                startActivity(ir);
            }
        });
    }
}