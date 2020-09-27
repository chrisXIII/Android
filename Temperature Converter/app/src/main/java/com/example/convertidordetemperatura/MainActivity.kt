package com.example.convertidordetemperatura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_resultado.*

const val VALORKELVIN = 273.15
const val SHARED_PREF_VALOR = "VALOR"

class MainActivity : AppCompatActivity() {

    private lateinit var etTemperatura: EditText
    private lateinit var btnConvertirTemperatura : Button
    private lateinit var rgTipoTemperatura : RadioGroup

    private val sharedPreferences by lazy {
        getSharedPreferences(SHARED_PREF_VALOR, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()

        ultimoValorAgregado()
    }

    private fun setupUI()
    {
        etTemperatura = findViewById(R.id.etTemperatura)
        btnConvertirTemperatura = findViewById(R.id.btnConvertirTemperatura)
        rgTipoTemperatura = findViewById(R.id.rgTipoTemperatura)

        btnConvertirTemperatura.setOnClickListener{
            convertirTemperatura()
        }
    }

    private fun ultimoValorAgregado() {

        val ultimovalor = sharedPreferences.getString(SHARED_PREF_VALOR, "" )

        etTemperatura.text = Editable.Factory.getInstance().newEditable(ultimovalor)
    }

    private fun convertirACelcius(temperatura : Double) = temperatura - VALORKELVIN

    private fun convertirAFarenheit(temperatura: Double) = (temperatura - VALORKELVIN) * 9/5 + 32


    private fun obtenerSeleccionRadioButton(): Int {
        return rgTipoTemperatura.checkedRadioButtonId
    }

    private fun convertirTemperatura() {
        val temperatura = etTemperatura.text.toString()

        if (temperatura.isNotEmpty()) {
            val resultado = when (obtenerSeleccionRadioButton()) {
                R.id.rBCelsius -> convertirACelcius(temperatura.toDouble())
                R.id.rBFarenheit -> convertirAFarenheit(temperatura.toDouble())
                else ->  convertirACelcius(temperatura.toDouble())
            }


            val intent = Intent(this,ResultadoActivity::class.java)
            intent.putExtra("RESULTADO" , resultado)
            startActivity(intent)

        } else {
            mensajeAdvertencia("Completar campo temperatura")
        }
    }

    private fun mensajeAdvertencia(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        sharedPreferences.edit().apply {
            putString(SHARED_PREF_VALOR, etTemperatura.text.toString())
            apply()
        }
        super.onStop()
    }


}