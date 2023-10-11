package com.example.buscadordevagas

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titulos = arrayOf("Estágio", "Desenvolvedor Jr.", "Tech Recruiter")
        val empresas = arrayOf("Suzano", "Google", "Microsoft")
        val locais = arrayOf("Suzano/SP", "São Paulo/SP", "Rio de Janeiro/RJ")
        val imagensArray = arrayOf(
            R.drawable.logo_suzano,
            R.drawable.logo_google,
            R.drawable.logo_microsoft
        )

        val textViewTitulosIds = arrayOf(R.id.titul_vaga1, R.id.titul_vaga2, R.id.titul_vaga3)
        val textViewEmpresasIds = arrayOf(R.id.nome_empresa1, R.id.nome_empresa2, R.id.nome_empresa3)
        val textViewLocaisIds = arrayOf(R.id.local1, R.id.local2, R.id.local3)
        val imageViewIds = arrayOf(R.id.logo_vaga1, R.id.logo_vaga2, R.id.logo_vaga3)

        for (i in 0 until 3) {
            val textViewTitulo = findViewById<TextView>(textViewTitulosIds[i])
            val textViewEmpresa = findViewById<TextView>(textViewEmpresasIds[i])
            val textViewLocal = findViewById<TextView>(textViewLocaisIds[i])
            val imageView = findViewById<ImageView>(imageViewIds[i])

            imageView.setImageResource(imagensArray[i])
            textViewTitulo.text = titulos[i]
            textViewEmpresa.text = empresas[i]
            textViewLocal.text = locais[i]
        }
    }
}
