package com.example.data.network.apiService

import com.example.data.dto.BreedDto
import com.example.data.network.constants.NetworkConstants
import retrofit2.http.GET

interface BreedsApiService {

    @GET(NetworkConstants.PATH)
    suspend fun getBreeds(): List<BreedDto>
}