package com.example.hesapmakinesiuygulama

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var input = ""
    private var operator = ""
    private var firstNumber = 0.0
    private var secondNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // sayı butonları tanımlaması
        val buttonNumbers = listOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        // operatörlerin tanımlanması
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonEqual = findViewById<Button>(R.id.buttonEqual)
        val buttonClear = findViewById<Button>(R.id.buttonClear)

        // sayı butonları tıklaması için seton clicker methodu
        for ((index, button) in buttonNumbers.withIndex()) {
            button.setOnClickListener {
                input += index.toString()
                resultTextView.text = input
            }
        }

        // operatör butonları için seton clicker methodu
        buttonAdd.setOnClickListener { setOperator("+") }
        buttonSubtract.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }

        buttonEqual.setOnClickListener {
            if (input.isNotEmpty() && operator.isNotEmpty()) {
                secondNumber = input.toDouble()
                val result = calculateResult()
                resultTextView.text = result.toString()
                input = ""
                operator = ""
            }
        }

        buttonClear.setOnClickListener {
            input = ""
            operator = ""
            firstNumber = 0.0
            secondNumber = 0.0
            resultTextView.text = "0"
        }
    }

    private fun setOperator(op: String) {
        if (input.isNotEmpty()) {
            firstNumber = input.toDouble()
            operator = op
            input = ""
        }
    }

    private fun calculateResult(): Double {
        return when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else 0.0
            else -> 0.0
        }
    }
}