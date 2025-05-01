package com.example.presentation.screen.breed_wikipedia

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.presentation.screen.breed_wikipedia.model.BreedWikipediaUiState
import com.example.presentation.screen.breed_wikipedia.navigation.BreedWikipediaRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BreedWikipediaViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {

    val args = savedStateHandle.toRoute<BreedWikipediaRoute>()

    private val _uiState = MutableStateFlow(BreedWikipediaUiState(args.url))
    val uiState = _uiState.asStateFlow()
}