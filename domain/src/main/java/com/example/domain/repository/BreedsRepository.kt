package com.example.domain.repository

import com.example.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {

    fun getBreeds(page: Int): Flow<List<Breed>>
    suspend fun toggleFavorite(breedId: String, isFavorite: Boolean)
}