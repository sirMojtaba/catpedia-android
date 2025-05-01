package com.example.domain.usecase

import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow

class GetBreedsUsecase(private val repository: BreedsRepository) {

    operator fun invoke(): Flow<List<Breed>> = repository.getBreeds()
}