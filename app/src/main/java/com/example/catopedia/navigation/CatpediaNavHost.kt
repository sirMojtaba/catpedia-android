package com.example.catopedia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screen.breed_detail.navigation.breedDetailScreen
import com.example.presentation.screen.breed_detail.navigation.navigateToBreedDetailScreen
import com.example.presentation.screen.breed_list.navigation.BreedsRoute
import com.example.presentation.screen.breed_list.navigation.breedsScreen
import com.example.presentation.screen.breed_wikipedia.navigation.breedWikipediaScreen
import com.example.presentation.screen.breed_wikipedia.navigation.navigateToBreedWikipediaScreen


@Composable
fun CatpediaNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BreedsRoute) {
        breedsScreen(
            onBreedClick = { breedId ->
                navController.navigateToBreedDetailScreen(breedId)
            }
        )

        breedDetailScreen(onUrlClick = { navController.navigateToBreedWikipediaScreen(it) })

        breedWikipediaScreen()
    }
}