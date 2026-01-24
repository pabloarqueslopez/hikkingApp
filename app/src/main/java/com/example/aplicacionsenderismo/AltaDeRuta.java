package com.example.aplicacionsenderismo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionsenderismo.Models.Ruta;
import com.example.aplicacionsenderismo.utiles.db.SenderismoDatabase;

public class AltaDeRuta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alta_de_ruta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Ejemplo de inserción en una Activity
    public void guardarNuevaRuta() {
        // 1. Creamos el objeto con tus datos
        Ruta miRuta = new Ruta("Ruta del Cares", "Asturias", 1, 2, 12.5f, "Muy bonita", "Llevar agua", true);

        // 2. Ejecutamos en un hilo secundario (OBLIGATORIO en Android)
        new Thread(() -> {
            SenderismoDatabase db = SenderismoDatabase.getDatabase(getApplicationContext());
            db.rutaDAO().insertar(miRuta);

            // 3. Si necesitas avisar a la UI, usa runOnUiThread
            runOnUiThread(() -> {
                Toast.makeText(this, "¡Ruta guardada!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}