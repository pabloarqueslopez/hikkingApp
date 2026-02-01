package com.example.aplicacionsenderismo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionsenderismo.Models.Ruta;
import com.example.aplicacionsenderismo.R;

import java.util.List;

public class AdaptadorRuta extends RecyclerView.Adapter<AdaptadorRuta.RutaViewHolder> {
    private List<Ruta> listaRutas;

    public AdaptadorRuta(List<Ruta> listRutas){
        this.listaRutas = listRutas;
    }

    @NonNull
    @Override
    public RutaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_ruta, parent, false);
        return new RutaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RutaViewHolder Rholder, int puesto){
        Ruta r = listaRutas.get(puesto);

        Rholder.nombre.setText(r.getNombreRuta());
        Rholder.distancia.setText(r.getDistanciaKm() + " KM");
        Rholder.dificultad.setText("Dificultad: "+r.getDificultad());
    }

    @Override
    public int getItemCount(){
        return listaRutas.size();
    }

    public void listaRefresh(List<Ruta> rutasNuevas){
        this.listaRutas = rutasNuevas;
        notifyDataSetChanged();
    }

    public static class RutaViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView distancia;
        TextView dificultad;
        ImageView imagen;

        public RutaViewHolder(@NonNull View v){
            super(v);
            nombre = v.findViewById(R.id.tvNombreRuta);
            distancia = v.findViewById(R.id.tvDistancia);
            dificultad = v.findViewById(R.id.tvDificultad);
            imagen = v.findViewById(R.id.ivIconoRuta);
        }
    }
}