package com.example.presentation.screen.breed_list.model

import androidx.compose.runtime.Immutable
import com.example.domain.model.Breed
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class BreedsUiState(
    val breeds: PersistentList<Breed> = persistentListOf(),
    val filteredBreeds: PersistentList<Breed> = persistentListOf(),
    val page: Int = 0,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val hasMore: Boolean = true,
    val isRefreshing: Boolean = false
)