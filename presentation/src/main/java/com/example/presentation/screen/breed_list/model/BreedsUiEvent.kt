package com.example.presentation.screen.breed_list.model

sealed interface BreedsUiEvent {
    data class UiMessage(val message: String) : BreedsUiEvent
    sealed interface Navigation : BreedsUiEvent {
        data class DetailScreen(val breedId: String) : Navigation
    }
}