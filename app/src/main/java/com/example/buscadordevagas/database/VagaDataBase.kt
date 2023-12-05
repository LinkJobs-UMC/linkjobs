package com.example.buscadordevagas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.buscadordevagas.dao.VagaDao
import com.example.buscadordevagas.entity.Vaga

@Database(entities = [Vaga::class], version = 2, exportSchema = false)
abstract class VagaDataBase : RoomDatabase() {
    abstract fun vagaDao(): VagaDao

    companion object {
        @Volatile
        private var INSTANCE: VagaDataBase? = null

        fun getDatabase(context: Context): VagaDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, VagaDataBase::class.java, "vagas"
                ).createFromAsset("database/vagas.db").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}