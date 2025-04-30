package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_image")
data class BreedImageEntity(
    @PrimaryKey
    val id: String,
    val url: String?
)
