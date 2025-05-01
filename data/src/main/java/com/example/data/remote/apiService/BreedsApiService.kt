package com.example.data.remote.apiService

import com.example.data.remote.dto.BreedDto
import com.example.data.remote.util.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface BreedsApiService {

    @GET(NetworkConstants.PATH)
    suspend fun getBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<BreedDto>
}