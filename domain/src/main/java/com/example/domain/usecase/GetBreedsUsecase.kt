package com.example.domain.usecase

import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository

class GetBreedsUsecase(private val repository: BreedsRepository) {

    suspend operator fun invoke(page: Int): List<Breed> = repository.getBreeds(page = page)
}