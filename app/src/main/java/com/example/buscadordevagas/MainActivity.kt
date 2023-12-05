package com.example.buscadordevagas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.buscadordevagas.view.VagaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vagaViewModel: VagaViewModel
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

        vagaViewModel = ViewModelProvider(this)[VagaViewModel::class.java]
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
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterData(s.toString())
            }
        })
        vagaViewModel.allVagas.observe(this) { vagas ->
            populateData()
            filterData(editText.text.toString())
        }
    }

    private fun populateData() {
        for (i in 0..2) {
            vagaViewModel.allVagas.observe(this) { vagas ->
                imageViewArray[i].setImageResource(imagensArray[i])
                textViewTitulos[i].text = vagas[i].titulo
                textViewEmpresas[i].text = vagas[i].empresa
                textViewLocais[i].text = vagas[i].local
            }
        }
    }

    private fun filterData(filterText: String) {
        for (i in 0..2) {
            vagaViewModel.allVagas.value?.let { vagas ->
                if (vagas.size > i && (vagas[i].titulo.contains(filterText, true) ||
                            vagas[i].empresa.contains(filterText, true) ||
                            vagas[i].local.contains(filterText, true))
                ) {
                    showContainer(i)
                    imageViewArray[i].setImageResource(imagensArray[i])
                    textViewTitulos[i].text = vagas[i].titulo
                    textViewEmpresas[i].text = vagas[i].empresa
                    textViewLocais[i].text = vagas[i].local
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
