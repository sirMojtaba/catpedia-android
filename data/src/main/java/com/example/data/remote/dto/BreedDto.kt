package com.example.data.remote.dto

import com.example.data.local.entity.BreedEntity
import com.example.domain.model.Breed
import com.example.domain.model.BreedImage
import java.util.UUID

data class BreedDto(
    val id: String? = null,
    val name: String? = null,
    val origin: String? = null,
    val description: String? = null,
    val temperament: String? = null,
    val life_span: String? = null,
    val wikipedia_url: String? = null,
    val image: BreedImageDto? = null
)

fun BreedDto.toDomain(): Breed {
    return Breed(
        id = id.orEmpty(),
        name = name.orEmpty(),
        origin = origin.orEmpty(),
        description = description.orEmpty(),
        temperament = temperament.orEmpty(),
        lifeSpan = life_span.orEmpty(),
        wikipediaUrl = wikipedia_url.orEmpty(),
        image = this@toDomain.image?.toDomain() ?: BreedImage(
            id = this@toDomain.image?.id.orEmpty(),
            url = this@toDomain.image?.url.orEmpty()
        )

    )
}

fun BreedDto.toEntity(): BreedEntity {
    return BreedEntity(
        id = this.id ?: UUID.randomUUID().toString(),
        name = this.name,
        origin = this.origin,
        description = this.description,
        temperament = this.temperament,
        lifeSpan = this.life_span,
        wikipediaUrl = this.wikipedia_url,
        image = this.image?.toEntity()
    )
}