package com.example.buscadordevagas.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.buscadordevagas.entity.Vaga

@Dao
interface VagaDao {
    @Query("SELECT * FROM vagas")
    fun getAllVagas(): LiveData<List<Vaga>>
    @Query("SELECT * FROM vagas WHERE id = :id")
    fun getVagaById(id: Int): Vaga?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVaga(vaga: Vaga): Long
}