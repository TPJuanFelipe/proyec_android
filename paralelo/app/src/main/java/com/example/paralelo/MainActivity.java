package com.example.paralelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        Button btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String informacion = "Esta es la información que se enviará al Layout2";

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("informacion", informacion);
                startActivity(intent);
            }
        });


    }



}