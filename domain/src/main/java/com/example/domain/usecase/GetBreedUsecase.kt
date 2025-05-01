package com.example.domain.usecase

import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow

class GetBreedUsecase(private val repository: BreedsRepository) {

    operator fun invoke(breedId: String): Flow<Breed> = repository.getBreed(breedId)
}