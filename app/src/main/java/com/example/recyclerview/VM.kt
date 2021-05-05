package com.example.recyclerview

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class VM (private val miRepositorio: JuegosRepositorio):ViewModel() {
    val listaJuegos: LiveData<MutableList<Juego>> =miRepositorio.listaJuegos.asLiveData()
    lateinit var miJuego:LiveData<Juego>

    fun insertar(miJuego: Juego) = viewModelScope.launch {
        miRepositorio.insertar(miJuego)
    }

    fun buscarPorId(id:Int) = viewModelScope.launch {
        miJuego=miRepositorio.buscarPorId(id).asLiveData()
    }

    fun borrar(miJuego: Juego) = viewModelScope.launch {
        miRepositorio.borrar(miJuego)
    }

    fun actualizar(miJuego: Juego) = viewModelScope.launch {
        miRepositorio.actualizar(miJuego)
    }

    fun borrarPorId(id:Int) = viewModelScope.launch {
        miRepositorio.borrarPorId(id)
    }
}

class JuegosViewModelFactory (private val miRepositorio: JuegosRepositorio) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(VM::class.java)){
            @Suppress("UNCHECKED_CAST")
            return VM(miRepositorio) as T
        }
        throw IllegalArgumentException("Clase ViewModel no reconocida")
    }
}