package com.example.recyclerview


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var formatterDate: Date? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setTitle("Juegos")
        setHasOptionsMenu(true)

        val imagen = view.findViewById<ImageView>(R.id.imagen)
        Glide.with(activity as MainActivity).load("https://loremflickr.com/320/240").into(imagen)

        view.findViewById<Button>(R.id.bFecha).setOnClickListener {
            // Create the date picker builder and set the title
            val builder = MaterialDatePicker.Builder.datePicker()
            // create the date picker
            val datePicker = builder.build()
            // set listener when date is selected
            datePicker.addOnPositiveButtonClickListener {
                // Create calendar object and set the date to be that returned from selection
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.time = Date(it)
                formatterDate =  convertStringToData(calendar.get(Calendar.DAY_OF_MONTH).toString() + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR))
                view.findViewById<TextView>(R.id.tvFecha).text = formatterDate.toString()
            }

            datePicker.show(getParentFragmentManager(), "MyTAG")
        }


        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.modificar)?.isVisible=false
        menu.findItem(R.id.guardar)?.isVisible=false
        menu.findItem(R.id.borrar)?.isVisible=false
    }

    fun convertStringToData(getDate: String?): Date? {
        var today: Date?=null
        val simpleDate = SimpleDateFormat("dd/MM/yyyy")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        try {
            today = simpleDate.parse(formatter.format(getDate))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return today
    }


}