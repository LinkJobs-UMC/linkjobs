package com.example.buscadordevagas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var vagaDao: VagaDao
    private lateinit var db: AppDatabase

    private lateinit var editText: EditText
    private lateinit var textViewTitulos: Array<TextView>
    private lateinit var textViewEmpresas: Array<TextView>
    private lateinit var textViewLocais: Array<TextView>
    private lateinit var imageViewArray: Array<ImageView>

    companion object {
        private val titulos = arrayOf("Estágio", "Desenvolvedor Jr.", "Tech Recruiter")
        private val empresas = arrayOf("Suzano", "Google", "Microsoft")
        private val locais = arrayOf("Suzano/SP", "São Paulo/SP", "Rio de Janeiro/RJ")
        private val imagensArray = arrayOf(
            R.drawable.logo_suzano,
            R.drawable.logo_google,
            R.drawable.logo_microsoft
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "nome_do_banco_de_dados"
        ).build()

        vagaDao = db.vagaDao()

        editText = findViewById(R.id.input_buscador)

        textViewTitulos = arrayOf(
            findViewById(R.id.titul_vaga0),
            findViewById(R.id.titul_vaga1),
            findViewById(R.id.titul_vaga2)
        )
        textViewEmpresas = arrayOf(
            findViewById(R.id.nome_empresa0),
            findViewById(R.id.nome_empresa1),
            findViewById(R.id.nome_empresa2)
        )
        textViewLocais =
            arrayOf(findViewById(R.id.local0), findViewById(R.id.local1), findViewById(R.id.local2))
        imageViewArray = arrayOf(
            findViewById(R.id.logo_vaga0),
            findViewById(R.id.logo_vaga1),
            findViewById(R.id.logo_vaga2)
        )

        populateData()

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterData(s.toString())
            }
        })
    }

    private fun populateData() {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..2) {
                val vaga = Vaga(
                    titulo = titulos[i],
                    empresa = empresas[i],
                    local = locais[i],
                    imagemResourceId = imagensArray[i]
                )
                // Insira a vaga no banco de dados
                vagaDao.insertVaga(vaga)
            }

            // Exibir os dados ao iniciar
            showAllVagasOnUI()
        }
    }

    private fun filterData(filterText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val vagas = vagaDao.getAllVagas()
            val filteredVagas = vagas.filter {
                it.titulo.contains(filterText, true) ||
                        it.empresa.contains(filterText, true) ||
                        it.local.contains(filterText, true)
            }

            withContext(Dispatchers.Main) {
                for (i in 0..2) {
                    if (i < filteredVagas.size) {
                        val vaga = filteredVagas[i]
                        imageViewArray[i].setImageResource(vaga.imagemResourceId)
                        textViewTitulos[i].text = vaga.titulo
                        textViewEmpresas[i].text = vaga.empresa
                        textViewLocais[i].text = vaga.local
                        showContainer(i)
                    } else {
                        imageViewArray[i].setImageResource(0)
                        textViewTitulos[i].text = ""
                        textViewEmpresas[i].text = ""
                        textViewLocais[i].text = ""
                        hideContainer(i)
                    }
                }
            }
        }
    }

    private fun showAllVagasOnUI() {
        CoroutineScope(Dispatchers.Main).launch {
            val vagas = vagaDao.getAllVagas()
            for (i in 0..2) {
                if (i < vagas.size) {
                    val vaga = vagas[i]
                    imageViewArray[i].setImageResource(vaga.imagemResourceId)
                    textViewTitulos[i].text = vaga.titulo
                    textViewEmpresas[i].text = vaga.empresa
                    textViewLocais[i].text = vaga.local
                    showContainer(i)
                } else {
                    imageViewArray[i].setImageResource(0)
                    textViewTitulos[i].text = ""
                    textViewEmpresas[i].text = ""
                    textViewLocais[i].text = ""
                    hideContainer(i)
                }
            }
        }
    }

    private fun hideContainer(index: Int) {
        when (index) {
            0 -> hideViewsInContainer(R.id.container_vaga0)
            1 -> hideViewsInContainer(R.id.container_vaga1)
            2 -> hideViewsInContainer(R.id.container_vaga2)
        }
    }

    private fun showContainer(index: Int) {
        when (index) {
            0 -> showViewsInContainer(R.id.container_vaga0)
            1 -> showViewsInContainer(R.id.container_vaga1)
            2 -> showViewsInContainer(R.id.container_vaga2)
        }
    }

    private fun hideViewsInContainer(containerId: Int) {
        val container = findViewById<View>(containerId)
        container.visibility = View.GONE
    }

    private fun showViewsInContainer(containerId: Int) {
        val container = findViewById<View>(containerId)
        container.visibility = View.VISIBLE
    }
}
