package com.muhammadsayed.moviedetails.presentation

sealed class MovieDetailsEvents {
    data class GetMovieDetails(val movieID: Int) : MovieDetailsEvents()
}