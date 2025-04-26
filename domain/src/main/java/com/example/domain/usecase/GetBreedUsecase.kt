package com.example.domain.usecase

import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository

class GetBreedUsecase(private val repository: BreedsRepository) {

    suspend operator fun invoke(): List<Breed> = repository.getBreeds()
}