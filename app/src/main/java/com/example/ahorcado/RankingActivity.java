package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.example.ahorcado.AlmacenPuntuacionesPHP;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import cz.msebera.android.httpclient.Header;

public class RankingActivity extends ListActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tv_prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        setListAdapter(
                new ArrayAdapter(this,
                        R.layout.elemento_lista,
                        R.id.titulo,
                        JuegoActivity.almacen.listaPuntuaciones(10)));



    }
}