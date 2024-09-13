package com.example.ultivictask.model.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "fruits")
data class Fruit(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val name: String,
    val description: String

) : Parcelable
