package com.example.domain.repository

import com.example.domain.model.Breed

interface BreedsRepository {

    suspend fun getBreeds(): List<Breed>
}