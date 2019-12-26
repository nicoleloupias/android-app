package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DificultadActivity extends AppCompatActivity {
    private TextView tv_nombre;
    private Button bt_facil;
    private Button bt_normal;
    private Button bt_dificil;
    private int dificultadElegida = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificultad);
        tv_nombre = findViewById(R.id.tv_nombre);
        bt_facil = findViewById(R.id.bt_facil);
        bt_normal = findViewById(R.id.bt_normal);
        bt_dificil = findViewById(R.id.bt_dificil);
        String nombre = getIntent().getStringExtra("NOMBRE");
        tv_nombre.setText(nombre);

    }

    public void dificultadClickeada(View v){

        switch (v.getId()){
            case R.id.bt_facil:
                dificultadElegida = 1;
                break;
            case R.id.bt_normal:
                dificultadElegida = 2;
                break;
            case R.id.bt_dificil:
                dificultadElegida = 3;
                break;
        }

        Intent i = new Intent(DificultadActivity.this, JuegoActivity.class);
        i.putExtra("DIFICULTAD", dificultadElegida);
        startActivity(i);
    }
}
