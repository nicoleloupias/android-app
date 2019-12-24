package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_tituloJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tituloJuego = findViewById(R.id.tv_tituloJuego);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/pigment-demo.regular.otf");
        tv_tituloJuego.setTypeface(face);


    }
}
