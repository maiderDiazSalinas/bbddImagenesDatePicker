package com.example.recyclerview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Juego::class), version=1, exportSchema = false)
abstract class BDJuegos:RoomDatabase() {

    abstract fun miDAO():JuegosDAO

    companion object{
        @Volatile
        private var INSTANCE: BDJuegos?=null

        fun getDatabase (context: Context): BDJuegos{
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    BDJuegos::class.java,
                    "juegos_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}