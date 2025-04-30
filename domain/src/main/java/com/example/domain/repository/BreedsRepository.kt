package com.example.domain.repository

import com.example.domain.model.Breed

interface BreedsRepository {

    suspend fun getBreeds(page: Int): List<Breed>
    suspend fun toggleFavorite(breedId: String, isFavorite: Boolean)
}