package com.example.data.network.apiService

import com.example.data.dto.BreedDto
import com.example.data.network.constants.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BreedsApiService {

    @GET(NetworkConstants.PATH)
    suspend fun getBreeds(
        @Header("x-api-key") apiKey: String, @Query("limit") limit: Int, @Query("page") page: Int
    ): List<BreedDto>
}