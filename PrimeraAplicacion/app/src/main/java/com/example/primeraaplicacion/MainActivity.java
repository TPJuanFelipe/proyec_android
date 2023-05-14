package com.example.primeraaplicacion;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    int contador ;
    int con=0;

    private EditText txtReinicio  ;
    private Button btnReinicio;


VideoView Vv;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vv=findViewById(R.id.Vid);
        String Video="android.resource://"+ getPackageName()+"/" + R.raw.perfil ;
        Vv.setVideoPath(Video);
        Vv.start();

        Vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp){
            mp.setLooping(true);}

        });






        txtReinicio = findViewById(R.id.txtReinicio);
        btnReinicio= findViewById(R.id.btnReinicio);
    }







    public void rei (View view) {
        String Reinicio = txtReinicio.getText().toString();
        if (Reinicio.length() > 0) {
            int numero = Integer.parseInt(Reinicio);
            contador=numero;

        }
        else {
            contador=0;
        }
        mostrarResultado();

    }





    public void incrementar(View vista) {
        contador = contador + 1;
        mostrarResultado();
    }

    public void decrementar(View vista) {
        contador--;
        mostrarResultado();
    }

    public void borrar(View vista) {
        contador = 0;
        mostrarResultado();
    }

    public void mostrarResultado() {
        TextView numero = findViewById(R.id.txt);
        numero.setText("Contador: " + contador);



    }




}


