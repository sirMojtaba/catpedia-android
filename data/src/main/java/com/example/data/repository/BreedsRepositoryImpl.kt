package com.example.data.repository

import com.example.data.local.dao.BreedDao
import com.example.data.local.entity.toDomain
import com.example.data.remote.apiService.BreedsApiService
import com.example.data.remote.constants.NetworkConstants
import com.example.data.remote.dto.toEntity
import com.example.domain.model.Breed
import com.example.domain.repository.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val api: BreedsApiService,
    private val dao: BreedDao
) : BreedsRepository {

    override fun getBreeds(): Flow<List<Breed>> =
        dao.getAllBreeds().map { list -> list.map { it.toDomain() } }

    override fun getBreed(breedId: String): Flow<Breed> = dao.getBreed(breedId)


    override suspend fun syncBreeds(page: Int) {
        val localFavorites = dao.getAllFavorites().first()
        val remoteResponse = api.getBreeds(
            NetworkConstants.API_KEY,
            limit = NetworkConstants.LIMIT,
            page = page
        )
        val entities = remoteResponse.map { dto ->
            val isFavorite = localFavorites.contains(dto.id)
            dto.toEntity().copy(isFavorite = isFavorite)
        }
        if (remoteResponse.isNotEmpty()) {
            dao.insertAll(entities)
        } else {
            throw Exception("The list is empty!")
        }
    }


    override suspend fun toggleFavorite(breedId: String, isFavorite: Boolean) {
        dao.updateFavorite(breedId, isFavorite)
    }
}