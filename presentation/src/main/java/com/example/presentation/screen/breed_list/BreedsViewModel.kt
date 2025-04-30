package com.example.presentation.screen.breed_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetBreedsUsecase
import com.example.presentation.model.BreedsUiState
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
    private val getBreedsUsecase: GetBreedsUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BreedsUiState>(BreedsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getBreeds()
    }

    fun getBreeds() {
        if (uiState.value.isLoading) return
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getBreedsUsecase(page = uiState.value.page)
            }.onSuccess { newBreeds ->
                if (newBreeds.isEmpty()) {
                    _uiState.update {
                        it.copy(isLoading = false, hasMore = false)
                    }
                    return@launch
                }
                val newList = (_uiState.value.breeds + newBreeds).distinctBy{ it.id }
                _uiState.update {
                    it.copy(
                        breeds = newList.toPersistentList(),
                        filteredBreeds = newList.toPersistentList(),
                        page = it.page + 1,
                        isLoading = false,
                        error = null
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        error = throwable.message,
                        isLoading = false
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
            getBreeds()
            _uiState.update {
                it.copy(
                    isRefreshing = false
                )
            }
        }
    }
}