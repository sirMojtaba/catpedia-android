package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.BreedImage

@Entity(tableName = "breed_image")
data class BreedImageEntity(
    @PrimaryKey
    val id: String,
    val url: String?
)

fun BreedImageEntity.toDomain(): BreedImage {
    return BreedImage(
        id = id,
        url = url.orEmpty()
    )
}
