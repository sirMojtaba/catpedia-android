package com.example.presentation.screen.breed_list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.screen.breed_list.BreedsRoute
import kotlinx.serialization.Serializable

@Serializable
data object BreedsNavigationRoute

fun NavGraphBuilder.breedsScreen(onBreedClick: (String) -> Unit) {
    composable<BreedsNavigationRoute> {
        BreedsRoute(onBreedClick = onBreedClick)
    }
}
