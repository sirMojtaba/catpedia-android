package com.example.presentation.screen.breed_list.model

sealed interface BreedsUiAction {
    data class Search(val query: String) : BreedsUiAction
    data class ToggleFavorite(val id: String) : BreedsUiAction
    data object LoadMore : BreedsUiAction
    data object Refresh: BreedsUiAction
    sealed interface Navigation : BreedsUiAction {
        data class DetailScreen(val breedId: String) : Navigation
    }
}