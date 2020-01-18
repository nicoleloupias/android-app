package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahorcado.AlmacenPuntuacionesPHP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;


public class RankingActivity extends ListActivity {
    private TextView tv_prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Vector<String> resultado = JuegoActivity.almacen.listaPuntuaciones();

        List<String> list = Collections.list(resultado.elements());

        ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(this,
                R.layout.activity_ranking, R.id.tv_resultados, list);

        // assign the list adapter
        setListAdapter(myAdapter);
    }
}