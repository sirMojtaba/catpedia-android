package com.example.presentation.screen.breed_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.screen.breed_detail.BreedDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class BreedDetailRoute(
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    val temperament: String,
    val lifeSpan: String,
    val wikipediaUrl: String,
    val imageUrl: String
)

fun NavController.navigateToBreedDetailScreen(
    id: String,
    name: String,
    origin: String,
    description: String,
    temperament: String,
    lifeSpan: String,
    wikipediaUrl: String,
    imageUrl: String
) =
    navigate(
        BreedDetailRoute(
            id,
            name,
            origin,
            description,
            temperament,
            lifeSpan,
            wikipediaUrl,
            imageUrl,
        )
    )

fun NavGraphBuilder.breedDetailScreen() {
    composable<BreedDetailRoute> {
        BreedDetailScreen {

        }
    }
}