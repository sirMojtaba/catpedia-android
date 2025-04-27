package com.example.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "breeds_screen") {
        composable(route = "breeds_screen") {
            BreedsScreen(
                onBreedClick = {
                    navController.navigate(route = "detail_screen")
                }
            )
        }

        composable(route = "detail_screen") { backStackEntry ->
            DetailScreen(onUrlClick = {navController.navigate("web_screen")})
        }

        composable(route = "web_screen") {
            WebScreen(url = "https://en.wikipedia.org/wiki/Abyssinian_(cat)")
        }
    }
}