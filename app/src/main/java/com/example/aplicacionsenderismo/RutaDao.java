package com.example.aplicacionsenderismo;

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

    @Query("SELECT * FROM ruta WHERE dificultad = :dificultad")
    List<Ruta> obtenerRutaPorDificultad();

    @Query("SELECT * FROM ruta WHERE nombreRuta LIKE '%' || :nombreRuta || '%'")
    List<Ruta> buscarRutaPorNombre();

    @Query("SELECT * FROM ruta WHERE id = :id")
    Ruta obtenerRutaPorId(int id);



}
