package com.example.presentation.screen.breed_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Breed
import com.example.domain.usecase.GetBreedsUsecase
import com.example.domain.usecase.SetBreedFavoriteUsecase
import com.example.domain.usecase.SyncBreedsUsecase
import com.example.presentation.screen.breed_list.model.BreedUiAction
import com.example.presentation.screen.breed_list.model.BreedsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val syncBreedsUsecase: SyncBreedsUsecase,
    private val getBreedsUsecase: GetBreedsUsecase,
    private val setBreedFavoriteUsecase: SetBreedFavoriteUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BreedsUiState>(BreedsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        syncBreeds()
        getBreeds()
    }

    fun onAction(action: BreedUiAction) {
        when (action) {
            BreedUiAction.LoadMore -> TODO()
            is BreedUiAction.Navigation.DetailScreen -> TODO()
            is BreedUiAction.Search -> TODO()
            is BreedUiAction.ToggleFavorite -> toggleFavorite(breedId = action.id)
        }
    }

    fun syncBreeds() {
        if (uiState.value.isLoading) return
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                syncBreedsUsecase(page = uiState.value.page)
            }.onSuccess {
                _uiState.update {
                    it.copy(page = it.page + 1, isLoading = false)
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(error = throwable.message, isLoading = false)
                }
            }
        }
    }

    fun getBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            getBreedsUsecase().collect { newBreeds ->
//                if (newBreeds.isEmpty()) {
//                    _uiState.update {
//                        it.copy(isLoading = false, hasMore = false)
//                    }
//                    return@collect
//                }
                _uiState.update {
                    it.copy(
                        breeds = newBreeds.toPersistentList(),
//                        page = it.page + 1,
                        isLoading = false,
                    )
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        val filteredList = if (query.isBlank()) {
            uiState.value.breeds
        } else {
            uiState.value.breeds.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        _uiState.update {
            it.copy(filteredBreeds = filteredList.toPersistentList())
        }
    }

    fun consumeError() {
        _uiState.update {
            it.copy(error = null)
        }
    }

    fun refreshBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(
                    breeds = persistentListOf(),
                    filteredBreeds = persistentListOf(),
                    page = 0,
                    hasMore = true,
                    isRefreshing = true
                )
            }
            syncBreeds()
            _uiState.update {
                it.copy(
                    isRefreshing = false
                )
            }
        }
    }

    private fun toggleFavorite(breedId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setBreedFavoriteUsecase(breedId)
        }
    }
}