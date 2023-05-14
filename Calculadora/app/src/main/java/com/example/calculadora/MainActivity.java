package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText etNumber2;
    EditText etNumber1;
    private Button btnAdd;
    private Button btnSubtract;
    private Button btnMultiply ;
    private Button btnDivide ;
    private TextView tvResult ;

    ImageView Enviar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
       Enviar = findViewById ( R.id.imaEnviar );
        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        btnAdd = findViewById(R.id.buttonAdd);
        btnSubtract = findViewById(R.id.buttonSubtract);
        btnMultiply = findViewById(R.id.buttonMultiply);
        btnDivide = findViewById(R.id.buttonDivide);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
    }

    private void calculate(char operator) {
        double number1 = Double.parseDouble(etNumber1.getText().toString());
        double number2 = Double.parseDouble(etNumber2.getText().toString());
        double result = 0;

        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    tvResult.setText("Error: División por cero");
                    return;
                }
                break;

        }



        double finalResult = result;
        Enviar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("result", finalResult );
                startActivity(intent);
            }
        });


    }





}






