package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.BreedEntity
import com.example.domain.model.Breed
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {

    @Upsert
    suspend fun insertAll(breeds: List<BreedEntity>)

    @Query("SELECT * FROM breeds")
    fun getAllBreeds(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breeds WHERE id = :breedId")
    fun getBreed(breedId: String): Flow<Breed>

    @Query("UPDATE breeds SET isFavorite = NOT isFavorite WHERE id = :breedId")
    suspend fun updateFavorite(breedId: String)

    @Query("SELECT id FROM breeds WHERE isFavorite= 1")
    fun getAllFavorites(): Flow<List<String>>

}