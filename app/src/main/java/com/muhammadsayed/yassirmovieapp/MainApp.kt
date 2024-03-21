package com.muhammadsayed.yassirmovieapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.muhammadsayed.navigation.NavGraph

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavGraph(
        navController = navController,
    )
}