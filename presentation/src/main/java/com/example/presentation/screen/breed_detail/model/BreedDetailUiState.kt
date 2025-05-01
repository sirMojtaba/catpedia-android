package com.example.presentation.screen.breed_detail.model

data class BreedDetailUiState(
    val id: String = "",
    val name: String = "",
    val origin: String = "",
    val description: String = "",
    val temperament: String = "",
    val lifeSpan: String = "",
    val wikipediaUrl: String = "",
    val imageUrl: String = "",
    val isFavorite: Boolean = false,
    val error: String = ""
)