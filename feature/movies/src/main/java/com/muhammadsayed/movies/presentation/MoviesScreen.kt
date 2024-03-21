package com.muhammadsayed.movies.presentation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
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
