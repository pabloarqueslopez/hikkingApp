package com.example.aplicacionsenderismo.DAO;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.aplicacionsenderismo.Models.PuntoDeInteres;

import java.util.List;

public interface DaoPuntoInteres {
    @Query("SELECT * FROM puntos_interes WHERE ruta_id = :ruta_id")
    List<PuntoDeInteres> obtenerPuntosInteresPorRuta(int ruta_id);

    @Insert
    void insertarPuntoDeInteres(PuntoDeInteres puntoDeInteres);
}
