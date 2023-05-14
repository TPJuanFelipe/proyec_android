package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );
        TextView tvResult = findViewById(R.id.respuesta2);
        double result = getIntent().getDoubleExtra("result", 0.0);
        tvResult.setText("Resultado: " + result);
    }

    public  void Regresar( View view ){
        finish ();
    };
}