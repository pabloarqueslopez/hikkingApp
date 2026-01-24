package com.example.aplicacionsenderismo.Models;

public class PuntoDeInteres {
    private int id;
    private String nombre;
    private String ruta_id;
    private String latitud;
    private String longitud;
    private String foto;

    public PuntoDeInteres(int id, String nombre, String ruta_id, String longitud, String latitud, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.ruta_id = ruta_id;
        this.longitud = longitud;
        this.latitud = latitud;
        this.foto = foto;
    }
}
