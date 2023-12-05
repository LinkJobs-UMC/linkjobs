package com.example.buscadordevagas.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.buscadordevagas.database.VagaDataBase
import com.example.buscadordevagas.entity.Vaga
import com.example.buscadordevagas.repository.VagaRepository

class VagaViewModel(application: Application) : AndroidViewModel(application) {

    val allVagas: LiveData<List<Vaga>>
    private val repository: VagaRepository

    init {
        val movieDao = VagaDataBase.getDatabase(application).vagaDao()
        repository = VagaRepository(movieDao)
        allVagas = repository.todasVagas
    }
}