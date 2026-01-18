package com.example.aplicacionsenderismo;

public class AltaRuta {
    private String nombreRuta;
    private String localizacion;
    private int tipoRuta; //esto sera 0 o 1 | 0 significara que es una ruta circular y 1 significara que es una ruta lineal.
    private int dificultad;
    private float distanciaKm;
    private String descripcion;
    private String notas;
    private boolean favorita;

    public AltaRuta(String nombreRuta, String localizacion, int tipoRuta, int dificultad, float distanciaKm, String descripcion, String notas, boolean favorita) {
        this.nombreRuta = nombreRuta;
        this.localizacion = localizacion;
        this.tipoRuta = tipoRuta;
        this.dificultad = dificultad;
        this.distanciaKm = distanciaKm;
        this.descripcion = descripcion;
        this.notas = notas;
        this.favorita = favorita;
    }
}
