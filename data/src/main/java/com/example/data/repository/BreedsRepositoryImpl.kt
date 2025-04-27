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
        return apiService.getBreeds("live_yiLUCdMhkSeU7S9JArlkNGAlQFEygxty4x9Efa2uGMlTJ8j2zfz3b8XjHHgvNseq").map {
            it.toDomain()
        }
    }
}