package com.example.profesor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private lateinit var editTextCantidadPastel: EditText
    private lateinit var editTextCantidadCazuela: EditText
    private lateinit var switchPropina: Switch
    private lateinit var textViewSubtotal: TextView
    private lateinit var textViewPropina: TextView
    private lateinit var textViewTotal: TextView

    private val valorPastel = 36000
    private val valorCazuela = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCantidadPastel = findViewById(R.id.cantidadPasteles)
        editTextCantidadCazuela = findViewById(R.id.cantidadCazuelas)
        switchPropina = findViewById(R.id.switch1)
        textViewSubtotal = findViewById(R.id.valorSubtotal)
        textViewPropina = findViewById(R.id.valorPropina)
        textViewTotal = findViewById(R.id.valorTotal)

        setEditTextListeners()
        switchPropina.setOnCheckedChangeListener { _, _ -> calcularTotales() }

        val buttonSalir: Button = findViewById(R.id.buttonSalir)
        buttonSalir.setOnClickListener {
            finish()
        }
    }

    private fun setEditTextListeners() {
        editTextCantidadPastel.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calcularTotales()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        editTextCantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calcularTotales()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun calcularTotales() {
        val cantidadPastel = obtenerCantidad(editTextCantidadPastel)
        val cantidadCazuela = obtenerCantidad(editTextCantidadCazuela)

        val subtotal = calcularSubtotal(cantidadPastel, cantidadCazuela)
        val propina = if (switchPropina.isChecked) calcularPropina(subtotal) else 0
        val total = subtotal + propina

        textViewSubtotal.text = formatCurrency(subtotal)
        textViewPropina.text = formatCurrency(propina)
        textViewTotal.text = formatCurrency(total)
    }

    private fun obtenerCantidad(editText: EditText): Int {
        val cantidadStr = editText.text.toString().trim()

        return if (cantidadStr.isNotEmpty()) {
            cantidadStr.toInt()
        } else {
            0
        }
    }

    private fun calcularSubtotal(cantidadPastel: Int, cantidadCazuela: Int): Int {
        return (cantidadPastel * valorPastel) + (cantidadCazuela * valorCazuela)
    }

    private fun calcularPropina(subtotal: Int): Int {

        return (0.1 * subtotal).toInt()
    }

    private fun formatCurrency(value: Int): String {
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        return format.format(value)
    }
}