package com.example.presentation.screen.breed_detail.model

sealed interface BreedUiEvent {
    sealed interface Navigation: BreedUiEvent {
        data class WikipediaScreen(val wikipediaUrl: String): Navigation
    }
}