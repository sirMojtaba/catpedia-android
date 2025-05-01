package com.example.presentation.screen.breed_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetBreedsUsecase
import com.example.domain.usecase.SetBreedFavoriteUsecase
import com.example.domain.usecase.SyncBreedsUsecase
import com.example.presentation.screen.breed_list.model.BreedsUiAction
import com.example.presentation.screen.breed_list.model.BreedsUiEvent
import com.example.presentation.screen.breed_list.model.BreedsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
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

    private val _uiEvent = MutableSharedFlow<BreedsUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        syncBreeds()
        getBreeds()
        search()
    }

    fun onAction(action: BreedsUiAction) {
        when (action) {
            BreedsUiAction.LoadMore -> syncBreeds()
            is BreedsUiAction.Navigation -> navigate(action)
            is BreedsUiAction.Search -> onSearchQueryChanged(query = action.query)
            is BreedsUiAction.ToggleFavorite -> toggleFavorite(breedId = action.id)
            BreedsUiAction.Refresh -> refreshBreeds()
        }
    }

    private fun syncBreeds() {
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
                    it.copy(isLoading = false)
                }
                _uiEvent.emit(BreedsUiEvent.UiMessage(throwable.message.toString()))
            }
        }
    }

    private fun getBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            getBreedsUsecase().collect { newBreeds ->
                _uiState.update {
                    it.copy(
                        breeds = newBreeds.toPersistentList(),
                        isLoading = false,
                    )
                }
            }
        }
    }

    private fun search() {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.distinctUntilChangedBy {
                it.searchQuery
            }.map {
                it.searchQuery
            }.collect { query ->
                _uiState.update {
                    it.copy(filteredBreeds = it.breeds.filter { breed ->
                        breed.name.contains(query, ignoreCase = true)
                    }.toPersistentList())
                }
            }
        }
    }

    private fun onSearchQueryChanged(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    private fun refreshBreeds() {
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

    private fun navigate(action: BreedsUiAction.Navigation) {
        when (action) {
            is BreedsUiAction.Navigation.DetailScreen -> {
                viewModelScope.launch {
                    _uiEvent.emit(BreedsUiEvent.Navigation.DetailScreen(action.breedId))
                }
            }
        }
    }
}