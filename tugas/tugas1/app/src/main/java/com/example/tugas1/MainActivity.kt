package com.example.tugas1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNilaiA = findViewById<EditText>(R.id.editTextNilaiA)
        val editTextNilaiB = findViewById<EditText>(R.id.editTextNilaiB)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMin = findViewById<Button>(R.id.btnMin)
        val btnTime = findViewById<Button>(R.id.btnTime)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val textViewHasil = findViewById<TextView>(R.id.textViewHasil)

        btnPlus.setOnClickListener() {
            val nilaiA = editTextNilaiA.text.toString().toInt()
            val nilaiB = editTextNilaiB.text.toString().toInt()
            val hasil = nilaiA + nilaiB
            textViewHasil.text = hasil.toString()
        }

        btnMin.setOnClickListener() {
            val nilaiA = editTextNilaiA.text.toString().toInt()
            val nilaiB = editTextNilaiB.text.toString().toInt()
            val hasil = nilaiA - nilaiB
            textViewHasil.text = hasil.toString()
        }

        btnTime.setOnClickListener() {
            val nilaiA = editTextNilaiA.text.toString().toInt()
            val nilaiB = editTextNilaiB.text.toString().toInt()
            val hasil = nilaiA * nilaiB
            textViewHasil.text = hasil.toString()
        }

        btnDivide.setOnClickListener() {
            val nilaiA = editTextNilaiA.text.toString().toInt()
            val nilaiB = editTextNilaiB.text.toString().toInt()
            val hasil = nilaiA / nilaiB
            textViewHasil.text = hasil.toString()
        }

        btnClear.setOnClickListener() {
            editTextNilaiA.text = null
            editTextNilaiB.text = null
            textViewHasil.text = null
        }
    }
}