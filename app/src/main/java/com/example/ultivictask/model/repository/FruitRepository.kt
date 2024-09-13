package com.example.ultivictask.model.repository

import com.example.ultivictask.model.local.entities.Fruit
import com.example.ultivictask.model.local.dao.FruitDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FruitRepository @Inject constructor(private val fruitDao: FruitDao) {
    fun searchFruits(query: String): Flow<List<Fruit>> = fruitDao.searchFruits(query)
    fun getAllFruits(): Flow<List<Fruit>> = fruitDao.getAllFruits()
    suspend fun insertFruit(fruit: Fruit) = fruitDao.insert(fruit)
    suspend fun deleteFruit(fruit: Fruit) = fruitDao.delete(fruit)
}
