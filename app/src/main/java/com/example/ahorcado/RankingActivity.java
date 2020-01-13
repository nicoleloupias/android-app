package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RankingActivity extends AppCompatActivity {
    private ArrayList usuario, puntuacion;
    private TextView tv_prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        tv_prueba = findViewById(R.id.tv_prueba);
        usuario = new ArrayList();
        puntuacion = new ArrayList();

        obtenerDatos();
    }

    private void obtenerDatos(){
        usuario.clear();
        puntuacion.clear();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://hangmanpmov.000webhostapp.com/connect/connect.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    try {
                        JSONArray jsonarray = new JSONArray(new String(responseBody));
                        for (int i = 0; i < jsonarray.length(); i++){
                            usuario.add(jsonarray.getJSONObject(i).getString("USER"));
                            puntuacion.add(jsonarray.getJSONObject(i).getString("SCORE"));
                        }
                        tv_prueba.setText(String.valueOf(usuario.get(0)) + String.valueOf(puntuacion.get(0)));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
