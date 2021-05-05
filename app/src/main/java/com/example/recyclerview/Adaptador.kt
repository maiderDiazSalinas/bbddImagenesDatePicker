package com.example.recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adaptador(var listaJuegos:MutableList<Juego>, val actividad: Activity):RecyclerView.Adapter<Adaptador.ViewHolder>() {
    inner class ViewHolder (v: View):RecyclerView.ViewHolder(v){
        var tvId:TextView=v.findViewById(R.id.item_tvId)
        var tvNombre: TextView=v.findViewById(R.id.item_tvNombre)
        var tvJugadores:TextView=v.findViewById(R.id.item_tvJugadores)
        var urlImagen: ImageView =v.findViewById(R.id.item_imagen)
        var objetivo:String=""
        var posicion:Int=0
        init{
            v.setOnClickListener {
                val bundle= bundleOf("id" to this.posicion)
                (actividad as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.action_SecondFragment_to_thirdFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvId.text=listaJuegos[position].id.toString()
        holder.tvNombre.text=listaJuegos[position].nombre
        holder.tvJugadores.text=String.format("Número de jugadores: ${listaJuegos[position].numJugadores}")
        Glide.with(actividad).load(listaJuegos[position].urlImagen).into(holder.urlImagen)
        holder.objetivo=listaJuegos[position].objetivo
        holder.posicion=listaJuegos[position].id
    }

    override fun getItemCount(): Int {
        return listaJuegos.count()
    }

}