package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity {
    private ImageView iv_muneco;
    private TextView tv_palabra;
    private TextView tv_letrasFalladas;
    private TextView tv_timer;
    private String palabraElegida;
    private String[] arrayPalabras;
    private Random random;
    private char[] arrayGuiones;
    private char[] arrayPalabra;
    private int intentos = 0;
    private boolean juegoTerminado = false;

    private MediaPlayer mpLetraIncorrecta;
    private MediaPlayer mpLetraCorrecta;

    Context contexto;


    private static long NUMERO_SEGUNDOS = 30000;
    private static final String SEGUNDOS_FORMAT = "%02d";
    private int segundosQueQuedan = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tv_palabra = findViewById(R.id.tv_palabra);
        tv_letrasFalladas = findViewById(R.id.tv_letrasFalladas);
        tv_timer = findViewById(R.id.tv_temporizador);
        iv_muneco = findViewById(R.id.iv_muneco);
        random = new Random();
        int dificultad = getIntent().getIntExtra("DIFICULTAD", 0);

        mpLetraIncorrecta = MediaPlayer.create(this, R.raw.fallo);
        mpLetraCorrecta = MediaPlayer.create(this, R.raw.acierto);

        contexto = this;

        switch (dificultad){
            case 1:
                arrayPalabras = getResources().getStringArray(R.array.array_facil);
                NUMERO_SEGUNDOS = 25000;
                break;
            case 2:
                arrayPalabras = getResources().getStringArray(R.array.array_normal);
                NUMERO_SEGUNDOS = 22000;
                break;
            case 3:
                arrayPalabras = getResources().getStringArray(R.array.array_dificil);
                NUMERO_SEGUNDOS = 20000;
                break;
        }
        palabraElegida = arrayPalabras[random.nextInt(arrayPalabras.length)].toUpperCase();
        tv_palabra.setText(numeroGuiones());
        arrayGuiones = numeroGuiones().toCharArray();
        arrayPalabra = palabraElegida.toCharArray();



//
        new CountDownTimer(NUMERO_SEGUNDOS, 1) {
            public void onTick(long millisUntilFinished) {
                if (Math.round((float)millisUntilFinished / 1000.0f) != segundosQueQuedan){
                    segundosQueQuedan = Math.round((float)millisUntilFinished / 1000.0f);
                }
                long redondear = segundosQueQuedan * 100;
                if(redondear==NUMERO_SEGUNDOS){
                    tv_timer.setText(segundosQueQuedan
                            + ":" + String.format(SEGUNDOS_FORMAT, 0));
                }else {
                    tv_timer.setText(segundosQueQuedan
                            + ":" + String.format(SEGUNDOS_FORMAT, millisUntilFinished % 100));
                }
            }

            public void onFinish() {
                tv_timer.setText("¡Has perdido! Te has quedado sin tiempo");
                iv_muneco.setImageResource(R.drawable.munequito_fallo6);
                juegoTerminado = true;
                new CuadroDialogo(contexto);
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
        if(intentos<6 && !juegoTerminado) {
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
            mpLetraIncorrecta.start();
            if(intentos==1){
                tv_letrasFalladas.setText(String.valueOf(l));
            }else {
                tv_letrasFalladas.setText(tv_letrasFalladas.getText().toString() + "   " + String.valueOf(l));
            }
        }else{
            mpLetraCorrecta.start();
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
                new CuadroDialogo(contexto);
                break;
        }
    }
}
