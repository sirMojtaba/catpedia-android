package com.example.data.repository

import com.example.data.local.dao.BreedDao
import com.example.data.local.entity.toDomain
import com.example.data.remote.apiService.BreedsApiService
import com.example.data.remote.constants.NetworkConstants
import com.example.data.remote.dto.toEntity
import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val api: BreedsApiService,
    private val dao: BreedDao
) : BreedsRepository {

    override suspend fun getBreeds(page: Int): List<Breed> {
        return try {
            val localFavorites = dao.getAllFavorites()
            val remoteResponse =
                api.getBreeds(NetworkConstants.API_KEY, limit = NetworkConstants.LIMIT, page = page)
            val entities = remoteResponse.map {
                val isFavorite = localFavorites.contains(it.id)
                it.toEntity().copy(isFavorite = isFavorite)
            }
            if (remoteResponse.isNotEmpty()) {
                dao.insertAll(entities)
            }
            dao.getAllBreeds().map {
                it.toDomain()
            }
        } catch (e: Exception) {
            dao.getAllBreeds().map { it.toDomain() }
        }
    }

    override suspend fun toggleFavorite(breedId: String, isFavorite: Boolean) {
        dao.updateFavorite(breedId, isFavorite)
    }
}