package com.example.recyclerview

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="tabla_juegos")
data class Juego(
    @PrimaryKey (autoGenerate = true) var id:Int=0,
    @NonNull @ColumnInfo (name= "nombre") var nombre:String,
    @NonNull @ColumnInfo (name="numJugadores") var numJugadores:Int,
    @NonNull @ColumnInfo (name="objetivo") var objetivo:String,
    @NonNull @ColumnInfo (name="imagen") var urlImagen:String
){}
