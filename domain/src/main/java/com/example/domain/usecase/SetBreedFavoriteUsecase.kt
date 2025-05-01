package com.example.domain.usecase

import com.example.domain.repository.BreedsRepository

class SetBreedFavoriteUsecase(private val repository: BreedsRepository) {

    suspend operator fun invoke(breedId: String) =
        repository.toggleFavorite(breedId)
}