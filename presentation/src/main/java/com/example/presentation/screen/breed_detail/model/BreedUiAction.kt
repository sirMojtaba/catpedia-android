package com.example.presentation.screen.breed_detail.model

sealed interface BreedUiAction {
    data class ToggleFavorite(val breedId: String): BreedUiAction
    sealed interface Navigation: BreedUiAction {
        data class WikipediaScreen(val wikipediaUrl: String): Navigation
    }
}