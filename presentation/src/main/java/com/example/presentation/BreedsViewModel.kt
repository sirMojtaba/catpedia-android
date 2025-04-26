package com.example.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetBreedsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getBreedsUsecase: GetBreedsUsecase
) : ViewModel() {

    fun getBreeds() {

    }

}