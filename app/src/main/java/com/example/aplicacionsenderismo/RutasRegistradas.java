package com.example.aplicacionsenderismo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionsenderismo.DAO.RutaDao;
import com.example.aplicacionsenderismo.Models.Ruta;
import com.example.aplicacionsenderismo.adapters.AdaptadorRuta;
import com.example.aplicacionsenderismo.utiles.db.SenderismoDatabase;

import java.util.ArrayList;
import java.util.List;

public class RutasRegistradas extends AppCompatActivity {

    private RecyclerView rvRutas;
    private AdaptadorRuta adaptadorRuta;
    private Spinner spDificultad;
    private SenderismoDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutas_registradas);

        db = SenderismoDatabase.getDatabase(this);
        rvRutas = findViewById(R.id.rvRutas);
        spDificultad = findViewById(R.id.spinnerDificultad);
        rvRutas.setLayoutManager(new LinearLayoutManager(this));
        adaptadorRuta = new AdaptadorRuta(new ArrayList<>());
        rvRutas.setAdapter(adaptadorRuta);

        SpinnerDificultad();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }

    private void SpinnerDificultad() {
        ArrayAdapter<CharSequence> spinner = ArrayAdapter.createFromResource(this, R.array.niveles_dificultad, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDificultad.setAdapter(spinner);

        spDificultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contadorFiltros(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void contadorFiltros(int dificultad){
        int min=0;
        int max=0;

        switch (dificultad){
            case 0:
                min = 1;
                max = 2;
                break;
            case 1:
                min = 3;
                max = 4;
                break;
            case 2:
                min =5;
                max = 5;
                break;
        }

        int finalMin = min;
        int finalMax = max;
        new Thread(() ->{
            List<Ruta> rutas = db.rutaDao().listadoFiltroDificultad(finalMin, finalMax);
            runOnUiThread(() -> {
                adaptadorRuta.listaRefresh(rutas);
            });
        }).start();
    }
}