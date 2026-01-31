package com.example.aplicacionsenderismo.DAO;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aplicacionsenderismo.Models.Ruta;

@Dao
public interface RutaDao {
    @Insert
    void insertar(Ruta ruta);

    @Delete
    void eliminar(Ruta ruta);

    @Update
    void actualizar(Ruta ruta);

    @Query("SELECT * FROM ruta ORDER BY id DESC")
    List<Ruta> obtenerTodasRutas();

    //filtra por dificultad
    @Query("SELECT * FROM ruta WHERE dificultad = :dificultad")
    List<Ruta> obtenerRutaPorDificultad(int dificultad);
    //filtra por nombre de ruta
    @Query("SELECT * FROM ruta WHERE nombreRuta LIKE '%' || :nombreRuta || '%'")
    List<Ruta> buscarRutaPorNombre(String nombreRuta);
}
