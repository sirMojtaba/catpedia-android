package com.example.data.di

import com.example.data.local.dao.BreedDao
import com.example.data.remote.apiService.BreedsApiService
import com.example.data.repository.BreedsRepositoryImpl
import com.example.domain.repository.BreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideBreedsRepository(apiService: BreedsApiService, dao: BreedDao): BreedsRepository =
        BreedsRepositoryImpl(apiService, dao)
}