package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var editNome: EditText
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var btnCalcular: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViews()
        setupClickListener()
    }
    
    private fun initViews() {
        editNome = findViewById(R.id.editNome)
        editPeso = findViewById(R.id.editPeso)
        editAltura = findViewById(R.id.editAltura)
        btnCalcular = findViewById(R.id.btnCalcular)
    }
    
    private fun setupClickListener() {
        btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }
    
    private fun calcularIMC() {
        val nome = editNome.text.toString().trim()
        val pesoStr = editPeso.text.toString().trim()
        val alturaStr = editAltura.text.toString().trim()
        
        // Validações
        if (nome.isEmpty()) {
            Toast.makeText(this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (pesoStr.isEmpty()) {
            Toast.makeText(this, "Por favor, digite seu peso", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (alturaStr.isEmpty()) {
            Toast.makeText(this, "Por favor, digite sua altura", Toast.LENGTH_SHORT).show()
            return
        }
        
        try {
            val peso = pesoStr.toDouble()
            val altura = alturaStr.toDouble()
            
            if (peso <= 0 || altura <= 0) {
                Toast.makeText(this, "Peso e altura devem ser valores positivos", Toast.LENGTH_SHORT).show()
                return
            }
            
            // Calcular IMC
            val imc = peso / (altura * altura)
            val classificacao = getClassificacaoIMC(imc)
            
            // Navegar para tela de resultado
            val intent = Intent(this, ResultadoActivity::class.java).apply {
                putExtra("NOME", nome)
                putExtra("PESO", peso)
                putExtra("ALTURA", altura)
                putExtra("IMC", imc)
                putExtra("CLASSIFICACAO", classificacao)
            }
            startActivity(intent)
            
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Por favor, digite valores válidos", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun getClassificacaoIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "Magreza leve"
            imc < 25.0 -> "Normal"
            imc < 30.0 -> "Sobrepeso"
            imc < 35.0 -> "Obesidade grau I"
            imc < 40.0 -> "Obesidade grau II"
            else -> "Obesidade grau III"
        }
    }
}