package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button bt_jugar;
    private MediaPlayer sonidoClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_jugar = findViewById(R.id.bt_jugar);
        sonidoClick = MediaPlayer.create(this, R.raw.click);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        bt_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonidoClick.start();
                Intent intent = new Intent(MainActivity.this, NombreActivity.class);
                startActivity(intent);
            }
        });

    }
}
