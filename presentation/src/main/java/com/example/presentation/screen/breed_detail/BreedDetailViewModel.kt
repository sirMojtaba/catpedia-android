package com.example.presentation.screen.breed_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.usecase.SetBreedFavoriteUsecase
import com.example.presentation.model.BreedDetailUiState
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
    private val setBreedFavoriteUsecase: SetBreedFavoriteUsecase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val args = savedStateHandle.toRoute<BreedDetailRoute>()

    private val _uiState = MutableStateFlow<BreedDetailUiState>(
        BreedDetailUiState(
            id = args.id,
            name = args.name,
            origin = args.origin,
            description = args.description,
            temperament = args.temperament,
            lifeSpan = args.lifeSpan,
            wikipediaUrl = args.wikipediaUrl,
            imageUrl = args.imageUrl,
            isFavorite = args.isFavorite
        )
    )
    val uiState = _uiState.asStateFlow()

    fun toggleFavorite(breedId: String, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = !isFavorite
            runCatching {
                setBreedFavoriteUsecase(breedId, isFavorite)
            }.onSuccess {
                _uiState.update {
                    it.copy(isFavorite = isFavorite)
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(error = throwable.message.toString())
                }
            }
        }
    }
}