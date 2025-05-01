package com.example.domain.usecase

import com.example.domain.repository.BreedsRepository

class SyncBreedsUsecase(private val repository: BreedsRepository) {

    suspend operator fun invoke(page: Int) = repository.syncBreeds(page = page)
}