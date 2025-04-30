package com.example.data.repository

import com.example.data.local.dao.BreedDao
import com.example.data.remote.dto.toDomain
import com.example.data.remote.apiService.BreedsApiService
import com.example.data.remote.constants.NetworkConstants
import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val api: BreedsApiService,
    private val dao: BreedDao
) : BreedsRepository {

    override suspend fun getBreeds(page: Int): List<Breed> {
        return api.getBreeds(NetworkConstants.API_KEY, limit = NetworkConstants.LIMIT, page = page).map {
            it.toDomain()
        }
    }
}