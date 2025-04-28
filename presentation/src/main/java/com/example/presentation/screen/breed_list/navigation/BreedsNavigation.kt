package com.example.presentation.screen.breed_list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.domain.model.Breed
import com.example.presentation.screen.breed_list.BreedsScreen
import kotlinx.serialization.Serializable

@Serializable
data object BreedsRoute

fun NavGraphBuilder.breedsScreen(onBreedClick: (Breed) -> Unit) {
    composable<BreedsRoute> {
        BreedsScreen(onBreedClick = onBreedClick)
    }
}
