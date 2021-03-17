package com.example.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var miRecyclerView:RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_second, container, false)
        val listaJuegos:MutableList<Juego> =cargarJuegos()
        miRecyclerView=rootView.findViewById(R.id.frag2_recyclerView)
        miRecyclerView.layoutManager=LinearLayoutManager(activity)
        miRecyclerView.adapter=Adaptador(listaJuegos)

        return rootView
    }

    fun cargarJuegos():MutableList<Juego>{
        val lista:MutableList<Juego> = mutableListOf()
        lista.add(Juego("Oca",4,"Conseguir llegar a la meta"))
        lista.add(Juego("parchis",4,"Conseguir llevar tus cuatro fichas a casa sin que el resto te coma"))
        lista.add(Juego("hotel",4,"Ir comprando terrenos, edificios y cobrando al resto de jugadores hasta que no puedan pagarte"))
        lista.add(Juego("sushi go",4,"Durante tres rondas ir consiguiendo puntos para ganar la mayor puntuación"))

        return lista
    }

}
