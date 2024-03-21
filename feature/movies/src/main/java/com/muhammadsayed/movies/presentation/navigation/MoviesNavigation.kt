package com.muhammadsayed.movies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muhammadsayed.common.util.Screen
import com.muhammadsayed.movies.presentation.MoviesScreen

fun NavGraphBuilder.moviesScreen(navController: NavController) {
    composable(Screen.MoviesScreen.route) {
        MoviesScreen(
            onNavigateDetailScreen = { id ->
                navController.navigate(Screen.DetailScreen.route + "/${id}")
            }
        )
    }
}