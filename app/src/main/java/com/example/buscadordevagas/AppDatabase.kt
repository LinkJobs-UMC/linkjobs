package com.example.buscadordevagas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Vaga::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun VagaDao(): VagaDao
}
