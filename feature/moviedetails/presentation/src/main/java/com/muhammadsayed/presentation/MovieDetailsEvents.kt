package com.muhammadsayed.presentation

sealed class MovieDetailsEvents {
    data class GetMovieDetails(val movieID: Int) : MovieDetailsEvents()
}