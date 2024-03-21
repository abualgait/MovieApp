package com.muhammadsayed.movies.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun MoviesScreen(onNavigateDetailScreen: (String) -> Unit) {
    val viewModel: MoviesViewModel = hiltViewModel()
    val trendingMoviesPagingItem = viewModel.state.collectAsLazyPagingItems()

    MoviesContent(
        trendingPagingItem = trendingMoviesPagingItem,
        onNavigateDetailScreen = onNavigateDetailScreen
    )
}
