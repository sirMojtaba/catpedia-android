package com.example.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Breed
import com.example.domain.model.BreedImage

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
    @Embedded(prefix = "img_")
    val image: BreedImageEntity?
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
        image = this@toDomain.image?.toDomain() ?: BreedImage(
            id = this@toDomain.image?.id.orEmpty(),
            url = this@toDomain.image?.url.orEmpty()
        )
    )
}
