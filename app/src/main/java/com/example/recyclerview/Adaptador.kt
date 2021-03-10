package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador:RecyclerView.Adapter<Adaptador.ViewHolder>() {
    class ViewHolder (v: View):RecyclerView.ViewHolder(v){
        var tvNumero: TextView
        init{
            tvNumero=v.findViewById(R.id.item_tvPalabras)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNumero.text="Este es el contenedor $position"
    }

    override fun getItemCount(): Int {
        return 10
    }

}