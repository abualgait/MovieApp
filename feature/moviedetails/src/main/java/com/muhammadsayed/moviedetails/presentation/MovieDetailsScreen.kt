package com.muhammadsayed.moviedetails.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun MovieDetailsScreen(movieId: Int, onNavigateBack: () -> Unit) {

    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    MovieDetailsContent(
        state = state,
        onNavigateBack = onNavigateBack, onRetry = {
            viewModel.onEvent(MovieDetailsEvents.GetMovieDetails(movieId))
        }
    )
}
