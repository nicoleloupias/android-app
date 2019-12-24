package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_tituloJuego;
    private Button bt_jugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tituloJuego = findViewById(R.id.tv_tituloJuego);
        bt_jugar = findViewById(R.id.bt_jugar);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/pigment-demo.regular.otf");
        tv_tituloJuego.setTypeface(face);
        bt_jugar.setTypeface(face);


    }
}
