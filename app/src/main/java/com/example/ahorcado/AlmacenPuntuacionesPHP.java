package com.example.ahorcado;

import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;

public class AlmacenPuntuacionesPHP implements AlmacenPuntuaciones{
    HttpURLConnection conexion;

    public Vector<String> listaPuntuaciones() {
        Vector<String> result = new Vector<String>();
        try {
            URL url=new URL("https://hangmanpmov.000webhostapp.com/connect/lista.php" + "?");
            conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                while (!linea.equals("")) {
                    result.add(linea);
                    linea = reader.readLine();
                }
                reader.close();
            } else {
                Log.e("Ahorcado", conexion.getResponseMessage());
            }
        } catch (Exception e) {
            Log.e("Ahorcado", e.getMessage(), e);
        } finally {
            if (conexion!=null) conexion.disconnect();
            return result;
        }
    }

    public void guardarPuntuacion(String nombre, int puntos) {
        try {
            URL url=new URL("https://hangmanpmov.000webhostapp.com/connect/insert.php?" +
                    "usuario="+ URLEncoder.encode(nombre, "UTF-8") + "&puntos="+
                    puntos);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                if (!linea.equals("OK")) {
                    Log.e("Ahorcado","Error en servicio Web nueva");
                }
            } else {
                Log.e("Ahorcado", conexion.getResponseMessage());
            }
        } catch (Exception e) {
            Log.e("Ahorcado", e.getMessage(), e);
        } finally {
            if (conexion!=null) conexion.disconnect();
        }
    }


}

