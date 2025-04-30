package com.example.domain.model

data class Breed(
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    val temperament: String,
    val lifeSpan: String,
    val wikipediaUrl: String,
    val isFavorite: Boolean,
    val image: BreedImage,
)
