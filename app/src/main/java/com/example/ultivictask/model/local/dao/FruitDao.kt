package com.example.ultivictask.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ultivictask.model.local.entities.Fruit
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fruit: Fruit)

    @Delete
    suspend fun delete(fruit: Fruit)

    @Query("SELECT * FROM fruits WHERE name LIKE '%' || :query || '%'")
    fun searchFruits(query: String): Flow<List<Fruit>>

    @Query("SELECT * FROM fruits")
    fun getAllFruits(): Flow<List<Fruit>>
}
