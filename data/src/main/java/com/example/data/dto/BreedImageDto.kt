package com.example.data.dto

import com.example.domain.model.BreedImage

data class BreedImageDto(
    val id: String? = null,
    val url: String? = null,
)

fun BreedImageDto.toDomain(): BreedImage {
    return BreedImage(
        id = id.orEmpty(),
        url = url.orEmpty()
    )
}
