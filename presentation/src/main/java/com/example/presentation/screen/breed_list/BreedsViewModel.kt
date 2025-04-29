package com.example.presentation.screen.breed_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Breed
import com.example.domain.usecase.GetBreedsUsecase
import com.example.presentation.model.BreedsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
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
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getBreedsUsecase(page = uiState.value.page)
            }.onSuccess { newBreeds ->
                if (newBreeds.isEmpty()) return@launch
                val newList = newBreeds.toPersistentList()
                _uiState.update {
                    it.copy(
                        breeds = (it.breeds + newList).toPersistentList(),
                        filteredBreeds = (it.breeds + newList).toPersistentList(),
                        page = it.page + 1,
                        loading = false,
                        error = null
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        error = throwable.message,
                        loading = false
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
}