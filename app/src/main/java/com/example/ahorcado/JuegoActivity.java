package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity {
    private ImageView iv_muneco, iv_carita;
    private TextView tv_palabra, tv_letrasFalladas, tv_puntuacion, tv_resultado, tv_maxima;
    private String palabraElegida, usuario, maximaPuntuacion;
    private String[] arrayPalabras;
    private Random random;
    private char[] arrayGuiones, arrayPalabra;
    private int intentos = 0;
    private int aciertos = 0;
    private int dificultad, puntos;
    private boolean juegoTerminado = false;
    private Dialog dialogo;
    private Button bt_reiniciar, bt_ranking, bt_cambiarDificultad;
    private boolean partidaGanada = false;
    private MediaPlayer mpLetraIncorrecta, mpLetraCorrecta, mpPartidaGanada, mpPartidaPerdida;
    private long puntuacion = 0;
    private long tiempoPasado;
    public static AlmacenPuntuaciones almacen;

    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        almacen = new AlmacenPuntuacionesPHP();
        tv_palabra = findViewById(R.id.tv_palabra);
        tv_letrasFalladas = findViewById(R.id.tv_letrasFalladas);
        iv_muneco = findViewById(R.id.iv_muneco);
        chronometer = findViewById(R.id.chronometer);
        random = new Random();
        dificultad = getIntent().getIntExtra("DIFICULTAD", 0);
        mpLetraIncorrecta = MediaPlayer.create(this, R.raw.fallo);
        mpLetraCorrecta = MediaPlayer.create(this, R.raw.acierto);
        mpPartidaGanada = MediaPlayer.create(this, R.raw.gana);
        mpPartidaPerdida = MediaPlayer.create(this, R.raw.pierde);
        dialogo = new Dialog(this);
        usuario = getIntent().getStringExtra("NOMBRE");
        switch (dificultad) {
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

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

    }

    private String numeroGuiones() {
        String numeroGuiones = "";
        for (int i = 0; i < palabraElegida.length(); i++) {
            numeroGuiones = numeroGuiones + "-";
        }
        return numeroGuiones;
    }

    public void letraClickeada(View v) {
        if (intentos < 6 && !juegoTerminado) {
            String letras = ((TextView) v).getText().toString();
            char letra = letras.charAt(0);
            v.setEnabled(false);
            v.setBackground(getResources().getDrawable(R.drawable.botonletradisabled));
            ((TextView) v).setTextColor(Color.rgb(102, 102, 102));
            comprobarYReemplazar(letra);
        }
    }

    private void comprobarYReemplazar(char l) {
        boolean acierto = false;
        for (int i = 0; i < palabraElegida.length(); i++) {
            if (l == arrayPalabra[i]) {
                arrayGuiones[i] = l;
                acierto = true;
            }
            tv_palabra.setText(String.valueOf(arrayGuiones));
        }
        if (!acierto) {
            intentos++;
            mpLetraIncorrecta.start();
            if (intentos == 1) {
                tv_letrasFalladas.setText(String.valueOf(l));
            } else {
                tv_letrasFalladas.setText(tv_letrasFalladas.getText().toString() + "   " + String.valueOf(l));
            }
        } else {
            mpLetraCorrecta.start();
            aciertos++;
        }

        switch (intentos) {
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
                juegoTerminado = true;
                break;
        }
        if (palabraElegida.equalsIgnoreCase(tv_palabra.getText().toString())) {
            partidaGanada = true;
            juegoTerminado = true;
        }
        if (juegoTerminado) {
            chronometer.stop();
            mostrarDialogo();
        }
    }

    private void mostrarDialogo() {
        dialogo.setContentView(R.layout.cuadro_dialogo);
        dialogo.setCancelable(false);
        tv_resultado = dialogo.findViewById(R.id.tv_resultado);
        iv_carita = dialogo.findViewById(R.id.iv_carita);
        if (partidaGanada) {
            tv_resultado.setText(getString(R.string.textoGanado));
            iv_carita.setImageResource(R.drawable.carita_feliz);
            mpPartidaGanada.start();
        } else {
            tv_resultado.setText(getString(R.string.textoPerdido));
            iv_carita.setImageResource(R.drawable.carita_triste);
            mpPartidaPerdida.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tv_puntuacion = dialogo.findViewById(R.id.tv_puntuacion);
        bt_ranking = dialogo.findViewById(R.id.bt_ranking);
        bt_cambiarDificultad = dialogo.findViewById(R.id.bt_cambiarDificultad);
        bt_reiniciar = dialogo.findViewById(R.id.bt_reiniciar);
        tv_maxima = dialogo.findViewById(R.id.tv_maxima);
        tiempoPasado = SystemClock.elapsedRealtime() - chronometer.getBase();
        int horasPasadas = (int) (tiempoPasado / 3600000);
        int minutosPasados = (int) (tiempoPasado - horasPasadas * 3600000) / 60000;
        int segundosPasados = (int) (tiempoPasado - horasPasadas * 3600000 - minutosPasados * 60000) / 1000;
        puntuacion = ((10000 * dificultad) * aciertos) / ((segundosPasados) * (intentos * intentos + 1));
        if (puntuacion < 0) {
            puntuacion = 0;
        }
        puntos = (int) Math.floor(puntuacion);
        almacen.guardarPuntuacion(usuario, puntos);
        tv_puntuacion.setText(getString(R.string.textoPuntuacion) + " " + puntos + "");
        maximaPuntuacion = almacen.maximaPuntuacion(usuario);
        tv_maxima.setText(getString(R.string.textoMaximaPuntuacion) + " " + maximaPuntuacion + "");
        bt_reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JuegoActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        bt_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JuegoActivity.this, RankingActivity.class);
                startActivity(i);
            }
        });
        bt_cambiarDificultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JuegoActivity.this, DificultadActivity.class);
                i.putExtra("NOMBRE", usuario);
                startActivity(i);
            }
        });

        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.show();
    }
}
