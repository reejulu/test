package com.example.milinear;

import java.util.ArrayList;

public class Puntuacion {
    private int id;
    private String nombre,tiempo,juego,record;
    private static ArrayList<Puntuacion> top = new ArrayList<Puntuacion>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Puntuacion(int id, String nombre, String tiempo, String juego, String record) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.juego = juego;
        this.record = record;
    }

    public Puntuacion() {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.juego = juego;
        this.record = record;
    }


    public static ArrayList<Puntuacion> getTop() {
        return top;
    }

    public static void setTop(ArrayList<Puntuacion> top) {
        Puntuacion.top = top;
    }

    @Override
    public String toString() {
        return "Puntuacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", juego='" + juego + '\'' +
                ", record='" + record + '\'' +
                '}';
    }
}
