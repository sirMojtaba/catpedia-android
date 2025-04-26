package com.example.data.dto

import com.example.domain.model.Breed

data class BreedDto(
    val id: String? = null,
    val name: String? = null,
    val origin: String? = null,
    val description: String? = null,
    val wikipediaUrl: String? = null,
    val image: BreedImageDto
)

fun BreedDto.toDomain(): Breed {
    return Breed(
        id = id.orEmpty(),
        name = name.orEmpty(),
        origin = origin.orEmpty(),
        description = description.orEmpty(),
        wikipediaUrl = wikipediaUrl.orEmpty(),
        image = image.toDomain()

    )
}

