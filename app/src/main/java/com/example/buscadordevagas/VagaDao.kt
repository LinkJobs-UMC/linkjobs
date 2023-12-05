package com.example.buscadordevagas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VagaDao {
    @Insert
    suspend fun insertVaga(vaga: Vaga): Long

    @Query("SELECT * FROM vagas")
    suspend fun getAllVagas(): List<Vaga>
}
