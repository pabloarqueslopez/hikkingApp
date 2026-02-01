package com.example.aplicacionsenderismo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionsenderismo.Models.Ruta;
import com.example.aplicacionsenderismo.utiles.db.SenderismoDatabase;

public class AltaDeRuta extends AppCompatActivity {

    private EditText txtBoxNombreRuta;
    private EditText localizacion;
    private EditText distancia;
    private EditText descripcion;
    private EditText notas;

    private RadioGroup tipoRuta;
    private RatingBar ratingBar;
    private Switch switchFavorito;
    private Button bntGuardar;
    private SenderismoDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alta_de_ruta);

        db = SenderismoDatabase.getDatabase(this);
        txtBoxNombreRuta = findViewById(R.id.txtBoxNombreRuta);
        localizacion = findViewById(R.id.localizacion);
        distancia = findViewById(R.id.nBoxDistancia);
        descripcion = findViewById(R.id.txtBoxDescripcion);
        notas = findViewById(R.id.txtBoxNotas);
        tipoRuta = findViewById(R.id.tipoRuta);
        ratingBar = findViewById(R.id.ratingBar);
        switchFavorito = findViewById(R.id.switchFavorito);
        bntGuardar = findViewById(R.id.btnGuardar);

        bntGuardar.setOnClickListener(v ->guardarNuevaRuta());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void guardarNuevaRuta() {
        //EXTRACCION DE DATOS DE LOS CAMPOS DE LA ACTIVIDAD
        String nombreRuta = txtBoxNombreRuta.getText().toString().trim();
        String localicacionRuta = localizacion.getText().toString().trim();
        String descripcionRuta = descripcion.getText().toString().trim();
        String notasRuta = notas.getText().toString().trim();

        String distanciaRuta = distancia.getText().toString();
        float distanciaEnKm = Float.parseFloat((distanciaRuta));
        int dificultadRuta = (int) ratingBar.getRating();

        int tipoDeRuta = 0;
        int tipoSeleccionada = tipoRuta.getCheckedRadioButtonId();
        if (tipoSeleccionada == R.id.rBtnLineal){
            tipoDeRuta = 1;
        }

        boolean favoritoRuta = switchFavorito.isChecked();

        //LLAMADA AL CONSTRUCTOR PARA CREAR UNA NUEVA RUTA CON ESTOS DATOS EXTRAIDOS
        Ruta r = new Ruta(nombreRuta,localicacionRuta,tipoDeRuta,dificultadRuta,distanciaEnKm,descripcionRuta,notasRuta,favoritoRuta);

        //CREACION DE HILO SECUNDARIO PARA EJECUCION DE LLAMADA A BD

        new Thread(() ->{
            try {
                db.rutaDao().insertar(r);
                runOnUiThread(() ->{
                    Toast.makeText(this, "Ruta guardada correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                });
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}