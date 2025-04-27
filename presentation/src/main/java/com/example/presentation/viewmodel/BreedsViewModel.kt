package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetBreedsUsecase
import com.example.presentation.model.BreedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsUsecase: GetBreedsUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BreedUiState>(BreedUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getBreeds()
    }

    fun getBreeds() {
        viewModelScope.launch {
            runCatching {
                getBreedsUsecase()
            }.onSuccess { breeds ->
                _uiState.update {
                    it.copy(
                        breeds = breeds.toPersistentList(),
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

}