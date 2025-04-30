package com.example.data.remote.dto

import com.example.data.local.entity.BreedImageEntity
import com.example.domain.model.BreedImage
import java.util.UUID

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

fun BreedImageDto.toEntity(): BreedImageEntity {
    return BreedImageEntity(
        id = this.id ?: UUID.randomUUID().toString(),
        url = this.url
    )
}