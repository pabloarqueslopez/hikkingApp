package com.example.aplicacionsenderismo.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ruta")
public class Ruta {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombreRuta;
    private String localizacion;
    private int tipoRuta; //esto sera 0 o 1 | 0 significara que es una ruta circular y 1 significara que es una ruta lineal.
    private int dificultad;
    private float distanciaKm;
    private String descripcion;
    private String notas;
    private boolean favorita;

    public Ruta(String nombreRuta, String localizacion, int tipoRuta, int dificultad, float distanciaKm, String descripcion, String notas, boolean favorita) {
        this.nombreRuta = nombreRuta;
        this.localizacion = localizacion;
        this.tipoRuta = tipoRuta;
        this.dificultad = dificultad;
        this.distanciaKm = distanciaKm;
        this.descripcion = descripcion;
        this.notas = notas;
        this.favorita = favorita;
    }

    public int getId() {
        return id;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public int getTipoRuta() {
        return tipoRuta;
    }

    public int getDificultad() {
        return dificultad;
    }

    public float getDistanciaKm() {
        return distanciaKm;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNotas() {
        return notas;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public void setTipoRuta(int tipoRuta) {
        this.tipoRuta = tipoRuta;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDistanciaKm(float distanciaKm) {

        this.distanciaKm = distanciaKm;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
