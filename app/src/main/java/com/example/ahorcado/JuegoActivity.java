package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity {
    private TextView tv_palabra;
    private String palabraElegida;
    private String[] arrayPalabras;
    private Random random;
    private char[] arrayGuiones;
    private char[] arrayPalabra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tv_palabra = findViewById(R.id.tv_palabra);
        random = new Random();
        int dificultad = getIntent().getIntExtra("DIFICULTAD", 0);

        switch (dificultad){
            case 1:
                arrayPalabras = getResources().getStringArray(R.array.array_facil);
                palabraElegida = arrayPalabras[random.nextInt(arrayPalabras.length)].toUpperCase();
                tv_palabra.setText(numeroGuiones(palabraElegida));
                break;
            case 2:
                arrayPalabras = getResources().getStringArray(R.array.array_normal);
                palabraElegida = arrayPalabras[random.nextInt(arrayPalabras.length)].toUpperCase();
                tv_palabra.setText(numeroGuiones(palabraElegida));
                break;
            case 3:
                arrayPalabras = getResources().getStringArray(R.array.array_dificil);
                palabraElegida = arrayPalabras[2].toUpperCase();
                tv_palabra.setText(numeroGuiones(palabraElegida));
                break;
        }
        arrayGuiones = numeroGuiones(palabraElegida).toCharArray();
        arrayPalabra = palabraElegida.toCharArray();
    }

    private String numeroGuiones(String palabraElegida){
        String numeroGuiones = "";
        for (int i = 0; i < palabraElegida.length(); i++){
            numeroGuiones = numeroGuiones + "-";
        }
        return numeroGuiones;
    }

    public void letraClickeada(View v) {
        String letraS = ((TextView) v).getText().toString();
        char letra = letraS.charAt(0);
        v.setEnabled(false);
        v.setBackgroundColor(Color.RED);
        comprobaryreemplazar(letra, palabraElegida, arrayGuiones, arrayPalabra);
    }

    private void comprobaryreemplazar(char l, String p, char[] arrayGuiones, char[] arrayPalabra){
        for (int i = 0; i < palabraElegida.length(); i++){
            if (l == arrayPalabra[i]){
                arrayGuiones[i] = l;
            }
            tv_palabra.setText(String.valueOf(arrayGuiones));
        }
    }
}
