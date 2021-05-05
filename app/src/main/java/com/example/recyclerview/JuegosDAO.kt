package com.example.recyclerview

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JuegosDAO {
    @Query("SELECT * FROM tabla_juegos ORDER BY id ASC")
    fun mostrarTodos(): Flow<MutableList<Juego>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar (miJuego: Juego)

    @Query ("DELETE FROM tabla_juegos")
    suspend fun borrarTodo()

    @Query ("SELECT * FROM tabla_juegos WHERE id LIKE :id")
    fun buscarPorId(id: Int):Flow<Juego>

    @Update
    suspend fun actualizar(miJuego: Juego)

    @Delete
    suspend fun borrar(miJuego: Juego)

    @Query("DELETE FROM tabla_juegos WHERE id LIKE :id")
    suspend fun borrarPorId(id:Int)
}