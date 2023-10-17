package com.example.buscadordevagas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val titulos = arrayOf("Estágio", "Desenvolvedor Jr.", "Tech Recruiter")
    private val empresas = arrayOf("Suzano", "Google", "Microsoft")
    private val locais = arrayOf("Suzano/SP", "São Paulo/SP", "Rio de Janeiro/RJ")
    private val imagensArray = arrayOf(
        R.drawable.logo_suzano,
        R.drawable.logo_google,
        R.drawable.logo_microsoft
    )

    private lateinit var editText: EditText
    private lateinit var textViewTitulos: Array<TextView>
    private lateinit var textViewEmpresas: Array<TextView>
    private lateinit var textViewLocais: Array<TextView>
    private lateinit var imageViewArray: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.input_buscador)

        textViewTitulos = arrayOf(
            findViewById(R.id.titul_vaga1),
            findViewById(R.id.titul_vaga2),
            findViewById(R.id.titul_vaga3)
        )
        textViewEmpresas = arrayOf(
            findViewById(R.id.nome_empresa1),
            findViewById(R.id.nome_empresa2),
            findViewById(R.id.nome_empresa3)
        )
        textViewLocais =
            arrayOf(findViewById(R.id.local1), findViewById(R.id.local2), findViewById(R.id.local3))
        imageViewArray = arrayOf(
            findViewById(R.id.logo_vaga1),
            findViewById(R.id.logo_vaga2),
            findViewById(R.id.logo_vaga3)
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
        for (i in 0 until 3) {
            imageViewArray[i].setImageResource(imagensArray[i])
            textViewTitulos[i].text = titulos[i]
            textViewEmpresas[i].text = empresas[i]
            textViewLocais[i].text = locais[i]
        }
    }

    private fun filterData(filterText: String) {
        if (filterText.isEmpty()) {
            populateData()
            for (i in 0 until 3) {
                showContainer(i)
            }
        } else {
            var matchFound = false
            for (i in 0 until 3) {
                if (titulos[i].contains(filterText, true) || empresas[i].contains(
                        filterText,
                        true
                    ) || locais[i].contains(filterText, true)
                ) {
                    imageViewArray[i].setImageResource(imagensArray[i])
                    textViewTitulos[i].text = titulos[i]
                    textViewEmpresas[i].text = empresas[i]
                    textViewLocais[i].text = locais[i]
                    matchFound = true
                    showContainer(i) // Mostra o contêiner correspondente
                } else {
                    imageViewArray[i].setImageResource(0)
                    textViewTitulos[i].text = ""
                    textViewEmpresas[i].text = ""
                    textViewLocais[i].text = ""
                    hideContainer(i) // Oculta o contêiner sem correspondência
                }
            }
            if (!matchFound) {
                populateData()
                for (i in 0 until 3) {
                    showContainer(i)
                }
            }
        }
    }

    private fun hideContainer(index: Int) {
        when (index) {
            0 -> hideViewsInContainer(R.id.container_vaga1)
            1 -> hideViewsInContainer(R.id.container_vaga2)
            2 -> hideViewsInContainer(R.id.container_vaga3)
        }
    }

    private fun showContainer(index: Int) {
        when (index) {
            0 -> showViewsInContainer(R.id.container_vaga1)
            1 -> showViewsInContainer(R.id.container_vaga2)
            2 -> showViewsInContainer(R.id.container_vaga3)
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