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
            BreedsScreen()
        }

        composable(route = "detail_screen") {
            DetailScreen()
        }
    }
}