package com.example.aplicacionsenderismo;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionsenderismo.DAO.DaoPuntoInteres;
import com.example.aplicacionsenderismo.DAO.RutaDao;
import com.example.aplicacionsenderismo.Models.PuntoDeInteres;
import com.example.aplicacionsenderismo.Models.Ruta;
import com.example.aplicacionsenderismo.utiles.db.SenderismoDatabase;

import java.util.List;

public class FichaTecnica extends AppCompatActivity {

    TextView txtTituloRuta;
    TextView txtDistancia;
    TextView txtDificultad;
    TextView txtTiempo;

    CheckBox checkBoxFavorito;
    RecyclerView rvPuntosInteres;

    int idRutaSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ficha_tecnica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtTituloRuta = findViewById(R.id.tituloRuta);
        txtDistancia = findViewById(R.id.DistanciaKm);
        txtDificultad = findViewById(R.id.dificultadValor);
        txtTiempo = findViewById(R.id.tiempo);
        checkBoxFavorito = findViewById(R.id.checkBox);
        rvPuntosInteres = findViewById(R.id.rVPuntosInteres);

        idRutaSeleccionado = getIntent().getIntExtra("idRuta", 0);

        if(idRutaSeleccionado == 0){
            Toast.makeText(this, "Error al cargar la ruta", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        rvPuntosInteres.setLayoutManager(new LinearLayoutManager(this));

        cargarDatos();

    }

    private void cargarDatos() {
        new Thread(() -> {

            SenderismoDatabase db = SenderismoDatabase.getDatabase(getApplicationContext());
            DaoPuntoInteres dao = db.DaoPuntoInteres();
            RutaDao rutaDao = db.rutaDao();


            Ruta ruta = rutaDao.obtenerRutaPorId(idRutaSeleccionado);

            List<PuntoDeInteres> listaPuntos = dao.obtenerPuntosInteresPorRuta(idRutaSeleccionado);

            runOnUiThread(() -> {
                if (ruta != null) {
                    txtTituloRuta.setText(ruta.getNombreRuta());
                    txtDistancia.setText(ruta.getDistanciaKm() + " km");

                    String difTexto = "Media";
                    if(ruta.getDificultad() <= 2) difTexto = "Fácil";
                    if(ruta.getDificultad() >= 4) difTexto = "Difícil";
                    txtDificultad.setText(difTexto);

                    checkBoxFavorito.setChecked(ruta.isFavorita());

                    txtTiempo.setText((int)(ruta.getDistanciaKm() * 15) + " min");
                }

                PuntosAdapter adaptador = new PuntosAdapter(listaPuntos);
                rvPuntosInteres.setAdapter(adaptador);
            });

        }).start();
    }
}