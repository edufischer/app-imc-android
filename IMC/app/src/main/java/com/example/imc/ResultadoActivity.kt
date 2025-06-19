package com.example.imc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class ResultadoActivity : AppCompatActivity() {
    
    private lateinit var textNome: TextView
    private lateinit var textClassificacao: TextView
    private lateinit var textIMC: TextView
    private lateinit var textPeso: TextView
    private lateinit var textAltura: TextView
    private lateinit var btnVoltar: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        
        initViews()
        displayResults()
        setupClickListener()
    }
    
    private fun initViews() {
        textNome = findViewById(R.id.textNome)
        textClassificacao = findViewById(R.id.textClassificacao)
        textIMC = findViewById(R.id.textIMC)
        textPeso = findViewById(R.id.textPeso)
        textAltura = findViewById(R.id.textAltura)
        btnVoltar = findViewById(R.id.btnVoltar)
    }
    
    private fun displayResults() {
        val nome = intent.getStringExtra("NOME") ?: ""
        val peso = intent.getDoubleExtra("PESO", 0.0)
        val altura = intent.getDoubleExtra("ALTURA", 0.0)
        val imc = intent.getDoubleExtra("IMC", 0.0)
        val classificacao = intent.getStringExtra("CLASSIFICACAO") ?: ""
        
        // Formatar valores com 2 casas decimais
        val decimalFormat = DecimalFormat("#.##")
        
        textNome.text = nome
        textClassificacao.text = classificacao
        textIMC.text = "Seu IMC ${decimalFormat.format(imc)}"
        textPeso.text = "Seu Peso ${decimalFormat.format(peso)}"
        textAltura.text = "Sua Altura ${decimalFormat.format(altura)}"
    }
    
    private fun setupClickListener() {
        btnVoltar.setOnClickListener {
            finish() // Volta para a MainActivity
        }
    }
}