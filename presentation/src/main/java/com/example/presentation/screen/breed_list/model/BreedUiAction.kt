package com.example.presentation.screen.breed_list.model

sealed interface BreedUiAction {
    data class Search(val query: String) : BreedUiAction
    data class ToggleFavorite(val id: String) : BreedUiAction
    data object LoadMore : BreedUiAction
    sealed interface Navigation : BreedUiAction {
        data class DetailScreen(val breedId: String) : Navigation
    }
}