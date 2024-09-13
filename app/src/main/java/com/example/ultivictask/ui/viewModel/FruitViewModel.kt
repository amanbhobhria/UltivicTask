package com.example.ultivictask.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ultivictask.model.local.entities.Fruit
import com.example.ultivictask.model.repository.FruitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(private val repository: FruitRepository) : ViewModel() {

    // LiveData for fruit list (initially loaded with demo data)
    private val _fruitList = MutableLiveData<List<Fruit>>()
    val fruitList: LiveData<List<Fruit>> = _fruitList

    // LiveData for selected fruits
    private val _selectedFruits = MutableLiveData<List<Fruit>>(emptyList())
    val selectedFruits: LiveData<List<Fruit>> = _selectedFruits

    // Initialize with demo fruits
    init {
        loadDemoFruits()
    }

    // Load demo fruits
    private fun loadDemoFruits() {
        _fruitList.value = listOf(
            Fruit(1, "Apple", "A sweet red fruit"),
            Fruit(2, "Banana", "A yellow long fruit"),
            Fruit(3, "Orange", "A juicy citrus fruit"),
            Fruit(4, "Mango", "A tropical stone fruit")
        )
    }

    // Search fruits by query
    fun searchFruits(query: String) {
        viewModelScope.launch {
            repository.searchFruits(query).collect { filteredFruits ->
                _fruitList.value = filteredFruits
            }
        }
    }

    // Insert fruit into the database
    fun insertFruit(fruit: Fruit) {
        viewModelScope.launch {
            repository.insertFruit(fruit)
        }
    }

    // Delete fruit from database and update selected fruits
    fun deleteFruit(fruit: Fruit) {
        viewModelScope.launch {
            repository.deleteFruit(fruit)
            _selectedFruits.value = _selectedFruits.value?.filter { it.id != fruit.id }
        }
    }

    // Add a fruit to the selected list
    fun addFruitToSelection(fruit: Fruit) {
        _selectedFruits.value = _selectedFruits.value?.plus(fruit)
    }

    // Remove a fruit from the selected list
    fun removeFruitFromSelection(fruit: Fruit) {
        _selectedFruits.value = _selectedFruits.value?.filter { it.id != fruit.id }
    }
}
