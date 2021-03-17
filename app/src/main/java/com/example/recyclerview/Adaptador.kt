package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var listaJuegos:MutableList<Juego>):RecyclerView.Adapter<Adaptador.ViewHolder>() {
    class ViewHolder (v: View):RecyclerView.ViewHolder(v){
        var tvId:TextView
        var tvNombre: TextView
        var tvJugadores:TextView
        var objetivo:String
        init{
            tvId=v.findViewById(R.id.item_tvId)
            tvNombre=v.findViewById(R.id.item_tvNombre)
            tvJugadores=v.findViewById(R.id.item_tvJugadores)
            objetivo=""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvId.text=position.toString()
        holder.tvNombre.text=listaJuegos[position].nombre
        holder.tvJugadores.text=String.format("Número de jugadores: ${listaJuegos[position].numJugadores}")
        holder.objetivo=listaJuegos[position].objetivo
    }

    override fun getItemCount(): Int {
        return listaJuegos.count()
    }

}