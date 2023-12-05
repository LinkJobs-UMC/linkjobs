package com.example.buscadordevagas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vagas")
data class Vaga(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "titulo")
    val titulo: String,

    @ColumnInfo(name = "empresa")
    val empresa: String,

    @ColumnInfo(name = "local")
    val local: String,

    @ColumnInfo(name = "imagemResourceId")
    val imagemResourceId: Int
)
