package com.example.miprimerapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtMensaje: TextView = findViewById(R.id.txtMensaje)
        val btnPulsar: Button = findViewById(R.id.btnPulsar)

        btnPulsar.setOnClickListener {
            contador++
            txtMensaje.text = "Has pulsado $contador veces"
        }
    }
}