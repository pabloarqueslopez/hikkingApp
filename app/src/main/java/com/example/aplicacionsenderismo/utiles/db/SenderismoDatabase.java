package com.example.aplicacionsenderismo.utiles.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aplicacionsenderismo.DAO.DaoPuntoInteres;
import com.example.aplicacionsenderismo.Models.Ruta;
import com.example.aplicacionsenderismo.Models.PuntoDeInteres;
import com.example.aplicacionsenderismo.DAO.RutaDao;

@Database(entities = {Ruta.class, PuntoDeInteres.class}, version = 2)
public abstract class SenderismoDatabase extends RoomDatabase {
    public abstract RutaDao rutaDao();
    public abstract DaoPuntoInteres DaoPuntoInteres();

    private static volatile SenderismoDatabase INSTANCE;

    public static SenderismoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SenderismoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SenderismoDatabase.class, "senderismo_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
