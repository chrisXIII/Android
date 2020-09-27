package com.example.convertidordetemperatura

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class ResultadoActivity : AppCompatActivity() {

    private lateinit var txtResultado : TextView
    private lateinit var btnVolver : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val bundle = intent.extras
        val resultado = bundle?.getDouble("RESULTADO") ?: 0

        SetupUI()

        txtResultado.text = resultado.toString()
    }

    fun SetupUI(){
        txtResultado =findViewById(R.id.txtResultado)
        btnVolver = findViewById(R.id.btnVolver)

        btnVolver.setOnClickListener{
            Volver()
        }
    }

    fun Volver(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }



    
}