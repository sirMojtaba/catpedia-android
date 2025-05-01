package com.example.data.remote.dto

import com.example.data.local.entity.BreedEntity
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class BreedDto(
    val id: String? = null,
    val name: String? = null,
    val origin: String? = null,
    val description: String? = null,
    val temperament: String? = null,
    @SerializedName("life_span")
    val lifeSpan: String? = null,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String? = null,
    val isFavorite: Boolean = false,
    val image: BreedImageDto? = null
)

fun BreedDto.toEntity(): BreedEntity {
    return BreedEntity(
        id = id ?: UUID.randomUUID().toString(),
        name = name,
        origin = origin,
        description = description,
        temperament = temperament,
        lifeSpan = lifeSpan,
        wikipediaUrl = wikipediaUrl,
        isFavorite = isFavorite,
        imageUrl = image?.url
    )
}