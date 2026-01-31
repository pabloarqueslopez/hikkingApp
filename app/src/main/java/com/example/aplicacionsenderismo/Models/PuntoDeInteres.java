package com.example.aplicacionsenderismo.Models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "puntos_interes", foreignKeys = @ForeignKey(entity = Ruta.class, parentColumns = "id", childColumns = "ruta_id", onDelete = CASCADE))
public class PuntoDeInteres {
    @PrimaryKey(autoGenerate = true)
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

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta_id() {
        return ruta_id;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getFoto() {
        return foto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRuta_id(String ruta_id) {
        this.ruta_id = ruta_id;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
