package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Breed

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
    val isFavorite: Boolean = false,
    val imageUrl: String?
)

fun BreedEntity.toDomain(): Breed {
    return Breed(
        id = id,
        name = name.orEmpty(),
        origin = origin.orEmpty(),
        description = description.orEmpty(),
        temperament = temperament.orEmpty(),
        lifeSpan = lifeSpan.orEmpty(),
        wikipediaUrl = wikipediaUrl.orEmpty(),
        isFavorite = isFavorite,
        imageUrl = imageUrl.orEmpty()
    )
}
