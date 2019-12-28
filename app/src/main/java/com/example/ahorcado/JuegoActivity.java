package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity {
    private ImageView iv_muneco;
    private TextView tv_palabra;
    private TextView tv_letrasFalladas;
    private TextView tvTimer;
    private String palabraElegida;
    private String[] arrayPalabras;
    private Random random;
    private char[] arrayGuiones;
    private char[] arrayPalabra;
    private int intentos = 0;
    private String arrayDeGuiones;

    private static final long NUMBER_MILLIS = 10000;
    private static final String MILLISECONDS_FORMAT = "%03d";
    private int secondsLeft = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tv_palabra = findViewById(R.id.tv_palabra);
        tv_letrasFalladas = findViewById(R.id.tv_letrasFalladas);
        tvTimer = findViewById(R.id.tv_temporizador);
        iv_muneco = findViewById(R.id.iv_muneco);
        random = new Random();
        int dificultad = getIntent().getIntExtra("DIFICULTAD", 0);

        switch (dificultad){
            case 1:
                arrayPalabras = getResources().getStringArray(R.array.array_facil);
                break;
            case 2:
                arrayPalabras = getResources().getStringArray(R.array.array_normal);
                break;
            case 3:
                arrayPalabras = getResources().getStringArray(R.array.array_dificil);
                break;
        }
        palabraElegida = arrayPalabras[random.nextInt(arrayPalabras.length)].toUpperCase();
        tv_palabra.setText(numeroGuiones());
        arrayGuiones = numeroGuiones().toCharArray();
        arrayPalabra = palabraElegida.toCharArray();



//
        new CountDownTimer(NUMBER_MILLIS, 1) {

            public void onTick(long millisUntilFinished) {

                if (Math.round((float)millisUntilFinished / 1000.0f) != secondsLeft){
                    secondsLeft = Math.round((float)millisUntilFinished / 1000.0f);
                }
                long roundMillis = secondsLeft * 1000;
                if(roundMillis==NUMBER_MILLIS){
                    tvTimer.setText(secondsLeft
                            + "." + String.format(MILLISECONDS_FORMAT, 0));
                }else {
                    tvTimer.setText("0"+secondsLeft
                            + "." + String.format(MILLISECONDS_FORMAT, millisUntilFinished % 1000));
                }
            }

            public void onFinish() {
                tvTimer.setText("done!");
            }
        }.start();
    }

    private String numeroGuiones(){
        String numeroGuiones = "";
        for (int i = 0; i < palabraElegida.length(); i++){
            numeroGuiones = numeroGuiones + "-";
        }
        return numeroGuiones;
    }

    public void letraClickeada(View v) {
        if(intentos<6) {
            String letras = ((TextView) v).getText().toString();
            char letra = letras.charAt(0);
            v.setEnabled(false);
            v.setBackground(getResources().getDrawable(R.drawable.botonletradisabled));
            ((TextView) v).setTextColor(Color.rgb(102, 102, 102));
            comprobarYReemplazar(letra);
        }
    }

    private void comprobarYReemplazar(char l){
        boolean acierto = false;
        for (int i = 0; i < palabraElegida.length(); i++){
            if (l == arrayPalabra[i]){
                arrayGuiones[i] = l;
                acierto = true;
            }
            tv_palabra.setText(String.valueOf(arrayGuiones));
        }
        if(!acierto){
            intentos++;
            if(intentos==1){
                tv_letrasFalladas.setText(String.valueOf(l));
            }else {
                tv_letrasFalladas.setText(tv_letrasFalladas.getText().toString() + "   " + String.valueOf(l));
            }
        }

        switch (intentos){
            case 1:
                iv_muneco.setImageResource(R.drawable.munequito_fallo1);
                break;
            case 2:
                iv_muneco.setImageResource(R.drawable.munequito_fallo2);
                break;
            case 3:
                iv_muneco.setImageResource(R.drawable.munequito_fallo3);
                break;
            case 4:
                iv_muneco.setImageResource(R.drawable.munequito_fallo4);
                break;
            case 5:
                iv_muneco.setImageResource(R.drawable.munequito_fallo5);
                break;
            case 6:
                iv_muneco.setImageResource(R.drawable.munequito_fallo6);
                break;
        }
    }
}
