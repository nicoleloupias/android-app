package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahorcado.AlmacenPuntuacionesPHP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;


public class RankingActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Vector<String> resultado = JuegoActivity.almacen.listaPuntuaciones();

        String[] array = resultado.toArray(new String[resultado.size()]);
        int pos = 0;
        String str = " - ";
        for (int i = 0; i < array.length; i++){
            int len = array[i].length();
            for (int j = 0; j < len; j++){
                if (array[i].charAt(j) == ' '){
                    pos = j;
                }
            }
            array[i] = addChar(array[i], str, pos);
        }



        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(array);
        recyclerView.setAdapter(mAdapter);

    }
    public String addChar(String str, String str2, int position) {
        return str.substring(0, position) + str2 + str.substring(position);
    }
}