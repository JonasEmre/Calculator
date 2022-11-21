package com.nezu.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var lastNumber: String = "0"
    private var lastOperator: String = ""
    private lateinit var tvResultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Others
        tvResultView = findViewById(R.id.tvResultView)

        // Number buttons
        val btnOne = findViewById<Button>(R.id.btnOne)
        val btnTwo = findViewById<Button>(R.id.btnTwo)
        val btnThree = findViewById<Button>(R.id.btnThree)
        val btnFour = findViewById<Button>(R.id.btnFour)
        val btnFive = findViewById<Button>(R.id.btnFive)
        val btnSix = findViewById<Button>(R.id.btnSix)
        val btnSeven = findViewById<Button>(R.id.btnSeven)
        val btnEight = findViewById<Button>(R.id.btnEight)
        val btnNine = findViewById<Button>(R.id.btnNine)
        val btnZero = findViewById<Button>(R.id.btnZero)

        // Operators
        val btnEquals = findViewById<Button>(R.id.btnEquals)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnDot = findViewById<Button>(R.id.btnDot)

        // General Buttons
        val btnClear = findViewById<Button>(R.id.btnClear)

        // Listeners for numbers
        btnOne.setOnClickListener { tvResultView.append(btnOne.text) }
        btnTwo.setOnClickListener { tvResultView.append(btnTwo.text) }
        btnThree.setOnClickListener { tvResultView.append(btnThree.text) }
        btnFour.setOnClickListener { tvResultView.append(btnFour.text) }
        btnFive.setOnClickListener { tvResultView.append(btnFive.text) }
        btnSix.setOnClickListener { tvResultView.append(btnSix.text) }
        btnSeven.setOnClickListener { tvResultView.append(btnSeven.text) }
        btnEight.setOnClickListener { tvResultView.append(btnEight.text) }
        btnNine.setOnClickListener { tvResultView.append(btnNine.text) }
        btnZero.setOnClickListener { tvResultView.append(btnZero.text) }

        // Listeners for operators
        btnEquals.setOnClickListener { }
        btnPlus.setOnClickListener { arithmeticOperator("+") }
        btnMinus.setOnClickListener { arithmeticOperator("-") }
        btnMultiply.setOnClickListener { arithmeticOperator("*") }
        btnDivide.setOnClickListener { arithmeticOperator("/") }

        // Adding , to the number to convert float value
        btnDot.setOnClickListener {
            if (!tvResultView.text.contains(",")) {
                tvResultView.append(",")
            }
        }

        // Listeners for others
        btnClear.setOnClickListener {
            tvResultView.text = ""
            lastNumber = "0"
            lastOperator = ""
        }
    }

    private fun arithmeticOperator(operator: String) {
        if (this.tvResultView.text == "") return

        lastOperator = operator

        if (lastNumber != "0") {
            val isFloat = (tvResultView.text.contains(",") || lastNumber.contains(","))

            val currentNumber = if (isFloat) {
                tvResultView.text.toString().toFloat()
            } else {
                tvResultView.text.toString().toInt()
            }

            var resultText = ""

            when (lastOperator) {
                "+" -> {
                    resultText =
                        if (isFloat) (currentNumber.toFloat() + lastNumber.toFloat()).toString()
                        else (currentNumber.toInt() + lastNumber.toInt()).toString()
                }
                "-" -> {
                    resultText =
                        if (isFloat) (currentNumber.toFloat() - lastNumber.toFloat()).toString()
                        else (currentNumber.toInt() - lastNumber.toInt()).toString()
                }
                "*" -> {
                    resultText = (currentNumber.toFloat() * lastNumber.toFloat()).toString()
                }
                "/" -> {
                    resultText = (currentNumber.toFloat() / lastNumber.toFloat()).toString()
                }
            }

            Toast.makeText(this, resultText, Toast.LENGTH_SHORT).show()
            lastNumber = resultText
            tvResultView.text = resultText
        } else {
            lastNumber = tvResultView.text.toString()
            tvResultView.text = ""
        }
    }


}