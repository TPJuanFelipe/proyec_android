package com.proyect.prestamo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.proyect.inven_sena.R;

public class vista_prestamo extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_vista_prestamo );
    }
}