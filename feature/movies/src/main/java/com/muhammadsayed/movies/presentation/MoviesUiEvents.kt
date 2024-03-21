package com.muhammadsayed.movies.presentation


sealed class MoviesUiEvents {
    data object GetTrendingMovies : MoviesUiEvents()
}