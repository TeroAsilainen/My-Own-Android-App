package com.example.datemanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class DbItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ean: String,
    val itemName: String,
    val expDate: String
)