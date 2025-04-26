package com.example.data.dto

data class BreedDto(
    val id: String? = null,
    val name: String? = null,
    val origin: String? = null,
    val description: String? = null,
    val wikipediaUrl: String? = null,
    val image: BreedImageDto? = null
)

