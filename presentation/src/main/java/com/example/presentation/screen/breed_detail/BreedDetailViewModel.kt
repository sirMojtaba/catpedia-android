package com.example.presentation.screen.breed_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.presentation.model.BreedDetailUiState
import com.example.presentation.screen.breed_detail.navigation.BreedDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val args = savedStateHandle.toRoute<BreedDetailRoute>()

    private val _uiState = MutableStateFlow<BreedDetailUiState>(
        BreedDetailUiState(
            name = args.name,
            origin = args.origin,
            description = args.description,
            temperament = args.temperament,
            lifeSpan = args.lifeSpan,
            wikipediaUrl = args.wikipediaUrl,
            imageUrl = args.imageUrl
        )
    )
    val uiState = _uiState.asStateFlow()

}