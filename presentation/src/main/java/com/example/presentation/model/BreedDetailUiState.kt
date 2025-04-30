package com.example.presentation.model

data class BreedDetailUiState(
    val id: String = "",
    val name: String = "",
    val origin: String = "",
    val description: String = "",
    val temperament: String = "",
    val lifeSpan: String = "",
    val wikipediaUrl: String = "",
    val imageUrl: String = "",
    val isFavorite: Boolean,
    val error: String = ""
)
