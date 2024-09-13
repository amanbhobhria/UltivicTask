package com.example.ultivictask.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ultivictask.model.local.entities.Fruit
import com.example.ultivictask.model.local.dao.FruitDao



@Database(entities = [Fruit::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fruitDao(): FruitDao
}