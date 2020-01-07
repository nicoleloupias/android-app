package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NombreActivity extends AppCompatActivity {
    private Button bt_ok;
    private EditText et_nombre;
    private MediaPlayer sonidoClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);
        et_nombre = findViewById(R.id.et_nombre);
        bt_ok = findViewById(R.id.bt_ok);
        sonidoClick = MediaPlayer.create(this, R.raw.click);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_nombre.getText().toString().isEmpty()) {
                    et_nombre.setError(getText(R.string.nombre_vacio));
                }else{
                    sonidoClick.start();
                    Intent intent = new Intent(NombreActivity.this, DificultadActivity.class);
                    intent.putExtra("NOMBRE", et_nombre.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
