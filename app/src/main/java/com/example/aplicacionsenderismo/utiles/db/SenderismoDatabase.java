package com.example.aplicacionsenderismo.utiles.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aplicacionsenderismo.DaoPuntoInteres;
import com.example.aplicacionsenderismo.Models.Ruta;
import com.example.aplicacionsenderismo.PuntoDeInteres;
import com.example.aplicacionsenderismo.RutaDao;

// Nota: Asegúrate de tener creadas las clases Ruta y RutaDao
@Database(entities = {Ruta.class, PuntoDeInteres.class}, version = 2)
public abstract class SenderismoDatabase extends RoomDatabase {



    // Método abstracto para obtener el DAO
    public abstract RutaDao rutaDao();
    public abstract DaoPuntoInteres DaoPuntoInteres();


    // La instancia única (Singleton)
    private static volatile SenderismoDatabase INSTANCE;

    public static SenderismoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SenderismoDatabase.class) {
                if (INSTANCE == null) {
                    // Creamos la base de datos
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SenderismoDatabase.class, "senderismo_db")
                            // Estrategia de migración simple para desarrollo
                            .build();
                }
            }
        }
        return INSTANCE;

}
