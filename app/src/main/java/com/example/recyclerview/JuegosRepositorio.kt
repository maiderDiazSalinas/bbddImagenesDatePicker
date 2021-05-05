package com.example.recyclerview

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class JuegosRepositorio (val miDAO: JuegosDAO) {
    val listaJuegos: Flow<MutableList<Juego>> =miDAO.mostrarTodos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertar(miJuego: Juego){
        miDAO.insertar(miJuego)
    }

    fun buscarPorId(id:Int): Flow<Juego> {
        return miDAO.buscarPorId(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrar(miJuego: Juego){
        miDAO.borrar(miJuego)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun actualizar(miJuego: Juego){
        miDAO.actualizar(miJuego)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrarPorId(id:Int){
        miDAO.borrarPorId(id)
    }

}