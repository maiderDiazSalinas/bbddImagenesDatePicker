package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

class ThirdFragment : Fragment() {

    var posicion:Int=0
    lateinit var etNombre: EditText
    lateinit var etJugadores: EditText
    lateinit var etObjetivo: EditText
    lateinit var miJuego:Juego
    lateinit var etImagen: EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        posicion=arguments?.getInt("id") ?:-1
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        val bInsertar=view.findViewById<Button>(R.id.frag2_bInsertar)
        val bBorrar=view.findViewById<Button>(R.id.frag2_bBorrar)
        val bModificar=view.findViewById<Button>(R.id.frag2_bModificar)
        etNombre=view.findViewById<EditText>(R.id.frag2_etNombre)
        etJugadores=view.findViewById<EditText>(R.id.frag2_etJugadores)
        etObjetivo=view.findViewById<EditText>(R.id.frag2_etObjetivos)
        etImagen=view.findViewById(R.id.frag2_urlImagen)
        val tvId=view.findViewById<TextView>(R.id.frag2_tvId)
        //posicion=arguments?.getInt("id") ?:-1

        if(posicion==-1){
            bBorrar.isEnabled=false
            bModificar.isEnabled=false
            bInsertar.isEnabled=true
            activity?.setTitle("Insertar juegos")
        }
        else{
            bBorrar.isEnabled=true
            bModificar.isEnabled=true
            bInsertar.isEnabled=false
            activity?.setTitle("Modificar juegos")
            (activity as MainActivity).miViewModel.buscarPorId(posicion)
            (activity as MainActivity).miViewModel.miJuego.observe(activity as MainActivity){juego->
                juego?.let {
                    miJuego = it
                    tvId.text = String.format("ID: $posicion")
                    etNombre.setText(juego.nombre)
                    etJugadores.setText(juego.numJugadores.toString())
                    etObjetivo.setText(juego.objetivo)
                    etImagen.setText(juego.urlImagen)
                }
            }
        }


        bInsertar.setOnClickListener {
            if(etNombre.text.isEmpty() || etJugadores.text.isEmpty() || etObjetivo.text.isEmpty() || etImagen.text.isEmpty())
                Toast.makeText(activity,"Tienes que insertar todos los datos", Toast.LENGTH_SHORT).show()
            else{
                (activity as MainActivity).miViewModel.insertar(Juego(nombre=etNombre.text.toString(),numJugadores =  etJugadores.text.toString().toInt(),objetivo =  etObjetivo.text.toString(), urlImagen = etImagen.text.toString()))
                findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
            }
        }

        bBorrar.setOnClickListener {
            (activity as MainActivity).miViewModel.borrarPorId(posicion)
            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
        }

        bModificar.setOnClickListener {
            if(miJuego.nombre==etNombre.text.toString() && miJuego.numJugadores==etJugadores.text.toString().toInt() && miJuego.objetivo==etObjetivo.text.toString() && miJuego.urlImagen==etImagen.text.toString()){
                Toast.makeText(activity,"No has modificado nada", Toast.LENGTH_SHORT).show()
            }
            else{
                (activity as MainActivity).miViewModel.actualizar(Juego(posicion, etNombre.text.toString(),etJugadores.text.toString().toInt(),etObjetivo.text.toString(),etImagen.text.toString()))
                findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
            }
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (posicion==-1){
            menu.findItem(R.id.modificar)?.isVisible=false
            menu.findItem(R.id.borrar)?.isVisible=false
        }
        else{
            menu.findItem(R.id.guardar)?.isVisible=false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.guardar->{
                if(etNombre.text.isEmpty() || etJugadores.text.isEmpty() || etObjetivo.text.isEmpty()|| etImagen.text.isEmpty())
                    Toast.makeText(activity,"Tienes que insertar todos los datos", Toast.LENGTH_SHORT).show()
                else{
                    (activity as MainActivity).miViewModel.insertar(Juego(nombre=etNombre.text.toString(),numJugadores =  etJugadores.text.toString().toInt(),objetivo =  etObjetivo.text.toString(), urlImagen = etImagen.text.toString()))
                    findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
                }
            }
            R.id.modificar->{
                if(miJuego.nombre==etNombre.text.toString() && miJuego.numJugadores==etJugadores.text.toString().toInt() && miJuego.objetivo==etObjetivo.text.toString() && miJuego.urlImagen==etImagen.text.toString()){
                    Toast.makeText(activity,"No has modificado nada", Toast.LENGTH_SHORT).show()
                }
                else{
                    (activity as MainActivity).miViewModel.actualizar(Juego(posicion, etNombre.text.toString(),etJugadores.text.toString().toInt(),etObjetivo.text.toString(),etImagen.text.toString()))
                    findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
                }
            }
            R.id.borrar->{
                (activity as MainActivity).miViewModel.borrarPorId(posicion)
                findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
