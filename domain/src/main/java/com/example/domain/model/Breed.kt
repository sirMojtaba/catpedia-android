package com.example.domain.model

data class Breed(
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    val wikipediaUrl: String,
    val image: BreedImage,
)
