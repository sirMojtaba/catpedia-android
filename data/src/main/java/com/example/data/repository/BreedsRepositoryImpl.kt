package com.example.data.repository

import com.example.data.dto.toDomain
import com.example.data.network.apiService.BreedsApiService
import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val apiService: BreedsApiService
) : BreedsRepository {

    override suspend fun getBreeds(): List<Breed> {
        return apiService.getBreeds().map {
            it.toDomain()
        }
    }
}