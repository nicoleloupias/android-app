package com.example.ahorcado;

import java.util.Vector;

interface AlmacenPuntuaciones {
    public void guardarPuntuacion(String nombre, int puntos);

    public Vector<String> listaPuntuaciones();
}
