package com.example.recyclerview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Transition
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

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

        var listaJuegos:MutableList<Juego> = mutableListOf()
        (activity as MainActivity).miViewModel.listaJuegos.observe(activity as MainActivity) { juegos->
            juegos?.let{listaJuegos=it}
            miRecyclerView=rootView.findViewById(R.id.frag2_recyclerView)
            miRecyclerView.layoutManager=LinearLayoutManager(activity)
            miRecyclerView.adapter=Adaptador(listaJuegos, activity as MainActivity)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.setTitle("Lista juegos")

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.modificar)?.isVisible=false
        menu.findItem(R.id.borrar)?.isVisible=false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.guardar->findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    /*fun cargarJuegos():MutableList<Juego>{
        val lista:MutableList<Juego> = mutableListOf()
        lista.add(Juego("Oca",4,"Conseguir llegar a la meta"))
        lista.add(Juego("parchis",4,"Conseguir llevar tus cuatro fichas a casa sin que el resto te coma"))
        lista.add(Juego("hotel",4,"Ir comprando terrenos, edificios y cobrando al resto de jugadores hasta que no puedan pagarte"))
        lista.add(Juego("sushi go",4,"Durante tres rondas ir consiguiendo puntos para ganar la mayor puntuación"))

        return lista
    }*/

}
