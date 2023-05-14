package com.example.apptp;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

public class menuActivity extends AppCompatActivity {
    VideoView Vv;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Vv=findViewById(R.id.Vidtp);
        String Video="android.resource://"+ getPackageName()+"/" + R.raw.per ;
        Vv.setVideoPath(Video);
        Vv.start();

        Vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp){
                mp.setLooping(true);}

        });






    }
    public void ircate(View view) {
        Intent interno= new Intent (menuActivity.this, Catego_herramiActivity.class);
        startActivity(interno);
    }




}