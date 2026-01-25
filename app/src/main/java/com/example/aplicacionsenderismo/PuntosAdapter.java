package com.example.aplicacionsenderismo;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PuntosAdapter extends RecyclerView.Adapter<PuntosAdapter.Visor> {

    // Lista de puntos que vamos a recibir
    List<PuntoDeInteres> listaPuntos;

    public PuntosAdapter(List<PuntoDeInteres> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    @NonNull
    @Override
    public Visor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Cargamos el diseño de la fila (asegúrate de tener item_punto.xml)
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.punto_interes_ruta, parent, false);
        return new Visor(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull Visor holder, int position) {
        PuntoDeInteres punto = listaPuntos.get(position);

        holder.txtNombre.setText(punto.getNombre());

        String coordenadas = "Lat: " + punto.getLatitud() + " | Lon: " + punto.getLongitud();
        holder.txtCoords.setText(coordenadas);

        holder.btnMapa.setOnClickListener(v -> {
            String uriTexto = "geo:" + punto.getLatitud() + "," + punto.getLongitud() + "?q=" + punto.getLatitud() + "," + punto.getLongitud() + "(" + punto.getNombre() + ")";
            Uri uri = Uri.parse(uriTexto);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaPuntos.size();
    }


    public class Visor extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtLongitud;
        TextView txtLatitud;

        TextView txtCoords;
        Button btnMapa;

        public Visor(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombrePunto);
            txtLatitud = itemView.findViewById(R.id.txtLatitud);
            txtLongitud = itemView.findViewById(R.id.txtLongitud);
            btnMapa = itemView.findViewById(R.id.btnVerMapaPunto);
        }
    }
}
