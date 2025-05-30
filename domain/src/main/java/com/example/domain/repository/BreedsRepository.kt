package com.example.domain.repository

import com.example.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface BreedsRepository {

    fun getBreeds(): Flow<List<Breed>>
    fun getBreed(breedId: String): Flow<Breed>
    suspend fun toggleFavorite(breedId: String)
    suspend fun syncBreeds(page: Int)
}