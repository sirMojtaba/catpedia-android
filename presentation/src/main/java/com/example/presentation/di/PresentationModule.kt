package com.example.presentation.di

import com.example.domain.repository.BreedsRepository
import com.example.domain.usecase.GetBreedsUsecase
import com.example.domain.usecase.SetBreedFavoriteUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideGetBreedsUsecase(repository: BreedsRepository): GetBreedsUsecase =
        GetBreedsUsecase(repository)

    @Provides
    @Singleton
    fun provideSetBreedFavorite(repository: BreedsRepository): SetBreedFavoriteUsecase =
        SetBreedFavoriteUsecase(repository)
}