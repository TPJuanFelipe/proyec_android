package com.proyect.categorias;



import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NuevaActividad extends AppCompatActivity {
    TextView nombre_cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_actividad);
        nombre_cate = findViewById ( R.id.nombre_cate );


        // Obtener los datos adicionales del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombreCategoria = extras.getString("nombre_categoria");
            // Hacer algo con el nombre de la categoría, como mostrarlo en un TextView
            nombre_cate.setText(nombreCategoria);
        }
    }
}
