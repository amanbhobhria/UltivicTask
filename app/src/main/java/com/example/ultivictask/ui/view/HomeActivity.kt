package com.example.ultivictask.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ultivictask.R
import com.example.ultivictask.databinding.ActivityHomeBinding
import com.example.ultivictask.model.local.entities.Fruit
import com.example.ultivictask.ui.adapter.FruitAdapter
import com.example.ultivictask.ui.viewModel.FruitViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit  var binding: ActivityHomeBinding
    private val viewModel: FruitViewModel by viewModels()
    private lateinit var adapter: FruitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FruitAdapter(viewModel::addFruitToSelection, viewModel::removeFruitFromSelection)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.fruitList.observe(this) { fruits ->
            adapter.submitList(fruits)
        }

        viewModel.selectedFruits.observe(this) { selectedFruits ->
            if (selectedFruits.isNotEmpty()) {
                binding.navigateButton.visibility = View.VISIBLE
            } else {
                binding.navigateButton.visibility = View.GONE
            }
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString()?.trim() ?: ""
                performSearch(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.navigateButton.setOnClickListener {
            val selectedFruits = viewModel.selectedFruits.value.orEmpty()
            if (selectedFruits.isNotEmpty()) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putParcelableArrayListExtra("selectedFruits", ArrayList(selectedFruits))
                }
                startActivity(intent)
            } else {
                // Show a toast message if no items are selected
                Toast.makeText(this, "Please select at least one item", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun performSearch(query: String) {
        val allFruits = viewModel.fruitList.value.orEmpty()
        if (query.isNotEmpty()) {
            val filteredList = allFruits.filter { it.name.contains(query, ignoreCase = true) }
            adapter.submitList(filteredList)
        } else {
            // If query is empty, show the full list again
            adapter.submitList(allFruits)
        }
    }






    override fun onBackPressed() {
        // If the search bar is not empty, clear it on back press
        if (binding.searchBar.text.isNotEmpty()) {
            binding.searchBar.text.clear()
        } else {
            // Call the default behavior (close the app)
            super.onBackPressed()
        }
    }

}