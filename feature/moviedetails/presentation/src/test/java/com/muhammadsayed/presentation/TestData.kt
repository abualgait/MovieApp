package com.muhammadsayed.presentation

import com.muhammadsayed.data.model.MovieDetailsApiModel
import com.muhammadsayed.data.model.MovieDetailsGenreApiModel

val movieDetail = MovieDetailsApiModel(
    backDropPath = "/abc123",
    genres = listOf(
        MovieDetailsGenreApiModel(id = 1, name = "Action"),
        MovieDetailsGenreApiModel(id = 2, name = "Adventure")
    ),
    id = 123456,
    overview = "This is a detailed overview of the movie.",
    releaseDate = "2023-01-01",
    title = "The Movie",
)
