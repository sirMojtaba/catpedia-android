package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class BreedEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val origin: String?,
    val description: String?,
    val temperament: String?,
    val lifeSpan: String?,
    val wikipediaUrl: String?,
    val image: BreedImageEntity?
)
