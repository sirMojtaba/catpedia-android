package com.example.presentation.screen.breed_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.usecase.GetBreedUsecase
import com.example.domain.usecase.SetBreedFavoriteUsecase
import com.example.presentation.screen.breed_detail.model.BreedDetailUiState
import com.example.presentation.screen.breed_detail.navigation.BreedDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val getBreedUsecase: GetBreedUsecase,
    private val setBreedFavoriteUsecase: SetBreedFavoriteUsecase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val args = savedStateHandle.toRoute<BreedDetailRoute>()

    private val _uiState =
        MutableStateFlow<BreedDetailUiState>(BreedDetailUiState(id = args.breedId))
    val uiState = _uiState.asStateFlow()

    init {
        getBreed()
    }

    private fun getBreed() {
        viewModelScope.launch(Dispatchers.IO) {
            getBreedUsecase(args.breedId).collect { breed ->
                _uiState.update {
                    it.copy(
                        name = breed.name,
                        origin = breed.origin,
                        description = breed.description,
                        temperament = breed.temperament,
                        lifeSpan = breed.lifeSpan,
                        wikipediaUrl = breed.wikipediaUrl,
                        imageUrl = breed.imageUrl,
                        isFavorite = breed.isFavorite,
                    )
                }
            }
        }
    }

    fun toggleFavorite(breedId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setBreedFavoriteUsecase(breedId)
        }
    }
}