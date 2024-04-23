package com.muhammadsayed.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muhammadsayed.common.util.Constants.MOVIE_ID
import com.muhammadsayed.common.util.Screen
import com.muhammadsayed.presentation.MovieDetailsScreen

fun NavGraphBuilder.movieDetailsScreen(navController: NavController) {
    composable(
        route = Screen.DetailScreen.route + "/{${MOVIE_ID}}",
        arguments = listOf(navArgument(MOVIE_ID) { type = NavType.IntType })
    ) {
        MovieDetailsScreen(
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}