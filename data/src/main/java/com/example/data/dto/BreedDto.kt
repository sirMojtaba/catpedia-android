package com.example.data.dto

import com.example.domain.model.Breed
import com.example.domain.model.BreedImage

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
        image = image?.toDomain() ?: BreedImage(
            id = image?.id.orEmpty(),
            url = image?.url.orEmpty()
        )

    )
}

