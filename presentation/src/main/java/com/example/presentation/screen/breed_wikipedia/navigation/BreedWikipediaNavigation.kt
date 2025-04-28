package com.example.presentation.screen.breed_wikipedia.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.screen.breed_wikipedia.BreedWikipediaScreen
import kotlinx.serialization.Serializable

@Serializable
data class BreedWikipediaRoute(val url: String)

fun NavController.navigateToBreedWikipediaScreen(url: String) = navigate(BreedWikipediaRoute(url))

fun NavGraphBuilder.breedWikipediaScreen() {
    composable<BreedWikipediaRoute> {
        BreedWikipediaScreen()
    }
}