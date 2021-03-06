package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DificultadActivity extends AppCompatActivity {
    private TextView tv_nombre;
    private Button bt_facil, bt_normal, bt_dificil, bt_ok;
    private int dificultadElegida = 0;
    private MediaPlayer sonidoClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificultad);
        tv_nombre = findViewById(R.id.tv_nombre);
        bt_facil = findViewById(R.id.bt_facil);
        bt_normal = findViewById(R.id.bt_normal);
        bt_dificil = findViewById(R.id.bt_dificil);
        bt_ok = findViewById(R.id.bt_ok2);
        sonidoClick = MediaPlayer.create(this, R.raw.click);

        final String nombre = getIntent().getStringExtra("NOMBRE");
        tv_nombre.setText(nombre);


        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dificultadElegida != 0) {
                    sonidoClick.start();
                    Intent i = new Intent(DificultadActivity.this, JuegoActivity.class);
                    i.putExtra("DIFICULTAD", dificultadElegida);
                    i.putExtra("NOMBRE", nombre);
                    startActivity(i);

                } else {
                    Toast.makeText(DificultadActivity.this, getText(R.string.dificultad_vacia), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void dificultadClickeada(View v) {
        switch (v.getId()) {
            case R.id.bt_facil:
                bt_facil.setBackground(getResources().getDrawable(R.drawable.botonrellenado2));
                bt_normal.setBackground(getResources().getDrawable(R.drawable.boton_clickeado_tam_2));
                bt_dificil.setBackground(getResources().getDrawable(R.drawable.boton_clickeado_tam_2));
                dificultadElegida = 1;
                break;
            case R.id.bt_normal:
                bt_normal.setBackground(getResources().getDrawable(R.drawable.botonrellenado2));
                bt_facil.setBackground(getResources().getDrawable(R.drawable.boton_clickeado_tam_2));
                bt_dificil.setBackground(getResources().getDrawable(R.drawable.boton_clickeado_tam_2));
                dificultadElegida = 2;
                break;
            case R.id.bt_dificil:
                bt_dificil.setBackground(getResources().getDrawable(R.drawable.botonrellenado2));
                bt_normal.setBackground(getResources().getDrawable(R.drawable.boton_clickeado_tam_2));
                bt_facil.setBackground(getResources().getDrawable(R.drawable.boton_clickeado_tam_2));
                dificultadElegida = 3;
                break;
        }
    }

}
