package com.example.ultivictask.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ultivictask.R
import com.example.ultivictask.model.local.entities.Fruit

class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve the selected fruits from the intent
        val selectedFruits = intent.getParcelableArrayListExtra<Fruit>("selectedFruits")

        // Display the selected fruits
        val selectedFruitsTextView = findViewById<TextView>(R.id.selectedFruitsTextView)
        selectedFruits?.let { fruits ->
            selectedFruitsTextView.text = fruits.joinToString("\n") { it.name }
        }

    }
}