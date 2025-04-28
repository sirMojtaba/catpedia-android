package com.example.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screen.breed_detail.BreedDetailScreen
import com.example.presentation.screen.breed_detail.navigation.navigateToBreedDetailScreen
import com.example.presentation.screen.breed_detail.navigation.breedDetailScreen
import com.example.presentation.screen.breed_list.navigation.BreedsRoute
import com.example.presentation.screen.breed_list.navigation.breedsScreen


@Composable
fun CatpediaNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BreedsRoute) {
        breedsScreen(
            onBreedClick = {
                navController.navigateToBreedDetailScreen(
                    it.id,
                    it.name,
                    it.origin,
                    it.description,
                    it.temperament,
                    it.lifeSpan,
                    it.wikipediaUrl,
                    it.image.url
                )
            }
        )

        breedDetailScreen()

        composable(route = "detail_screen") { backStackEntry ->
            BreedDetailScreen(onUrlClick = { navController.navigate("web_screen") })
        }

        composable(route = "web_screen") {
            WebScreen(url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)")
        }
    }
}