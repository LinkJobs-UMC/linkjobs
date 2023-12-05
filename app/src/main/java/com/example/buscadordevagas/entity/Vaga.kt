package com.example.buscadordevagas.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vagas")
data class Vaga(
    val titulo: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val empresa: String,
    val local: String,
    val imagemResourceId: Int
)