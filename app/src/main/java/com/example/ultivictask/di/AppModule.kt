package com.example.ultivictask.di


import android.content.Context
import androidx.room.Room
import com.example.ultivictask.model.local.dao.FruitDao
import com.example.ultivictask.model.local.database.AppDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "fruit_database"
        ).build()
    }

    @Provides
    fun provideFruitDao(database: AppDatabase): FruitDao {
        return database.fruitDao()
    }
}
