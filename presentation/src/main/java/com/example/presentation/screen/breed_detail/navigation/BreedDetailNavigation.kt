package com.example.presentation.screen.breed_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.screen.breed_detail.BreedDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class BreedDetailRoute(val breedId: String)

fun NavController.navigateToBreedDetailScreen(breedId: String) = navigate(BreedDetailRoute(breedId))

fun NavGraphBuilder.breedDetailScreen(onUrlClick: (String) -> Unit) {
    composable<BreedDetailRoute> {
        BreedDetailScreen(onUrlClick = onUrlClick)
    }
}