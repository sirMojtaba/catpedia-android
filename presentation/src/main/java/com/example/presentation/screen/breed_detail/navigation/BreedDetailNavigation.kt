package com.example.presentation.screen.breed_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.screen.breed_detail.BreedDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class BreedDetailNavigationRoute(val breedId: String)

fun NavController.navigateToBreedDetailScreen(breedId: String) =
    navigate(BreedDetailNavigationRoute(breedId))

fun NavGraphBuilder.breedDetailScreen(onUrlClick: (String) -> Unit) {
    composable<BreedDetailNavigationRoute> {
        BreedDetailRoute(onUrlClick = onUrlClick)
    }
}