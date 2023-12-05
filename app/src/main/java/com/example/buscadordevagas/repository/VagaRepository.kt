package com.example.buscadordevagas.repository

import androidx.lifecycle.LiveData
import com.example.buscadordevagas.dao.VagaDao
import com.example.buscadordevagas.entity.Vaga

class VagaRepository(private val vagaDao: VagaDao) {
    val todasVagas: LiveData<List<Vaga>> = vagaDao.getAllVagas()
}