package com.example.ahorcado;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CuadroDialogo {
    private Context contexto;
    public CuadroDialogo(final Context contexto){
        this.contexto = contexto;
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.cuadro_dialogo);

        // TextView resultado = dialogo.findViewById(R.id.tv_resultado);
        // TextView puntuacion = dialogo.findViewById(R.id.tv_puntuacion);
        Button reiniciar = dialogo.findViewById(R.id.bt_reiniciar);
        Button ranking = dialogo.findViewById(R.id.bt_ranking);
        Button cambiarDificultad = dialogo.findViewById(R.id.bt_cambiarDificultad);

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto,MainActivity.class);
                contexto.startActivity(i);
            }
        });
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto,DificultadActivity.class);
                contexto.startActivity(i);
            }
        });
        cambiarDificultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto,DificultadActivity.class);
                contexto.startActivity(i);
            }
        });

        dialogo.show();
    }
}
