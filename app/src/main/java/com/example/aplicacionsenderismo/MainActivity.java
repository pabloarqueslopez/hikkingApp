package com.example.aplicacionsenderismo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionsenderismo.utiles.db.SenderismoDatabase;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ejecutamos en un hilo secundario para que Room pueda trabajar
        new Thread(() -> {
            // Esta línea dispara la creación del archivo y las tablas
            SenderismoDatabase db = SenderismoDatabase.getDatabase(this);

            // Al pedir una consulta, obligas a Room a verificar/crear la estructura
            int cantidad = db.rutaDAO().obtenerTodasRutas().size();

            Log.d("DB_TEST", "La base de datos está lista. Rutas actuales: " + cantidad);
        }).start();

        Button buttonAboutUs = findViewById(R.id.aboutUs);
        Button buttonExit = findViewById(R.id.exit);


        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutUs(v);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void openAboutUs(View view){
        Intent intent = new Intent(this, AcercaDe.class);
        startActivity(intent);
    }

    private void mostrarUsuarios(){

    }

    private void insertarElementos(){

    }
}