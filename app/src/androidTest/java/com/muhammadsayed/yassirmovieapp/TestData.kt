package com.muhammadsayed.yassirmovieapp

import com.muhammadsayed.data.model.MovieDetailsApiModel
import com.muhammadsayed.data.model.MovieDetailsGenreApiModel
import com.muhammadsayed.movies.data.model.ResultApiModel

val resultList = listOf(
    ResultApiModel(
        adult = false,
        backdropPath = "/abc123",
        genreIds = listOf(28, 12, 80),
        id = 123456,
        originalLanguage = "en",
        originalTitle = "The Original Movie",
        overview = "This is a great movie!",
        popularity = 123.45,
        posterPath = "/def456",
        releaseDate = "2023-01-01",
        title = "The Movie",
        video = false,
        voteAverage = 7.5,
        voteCount = 1000
    ),
    ResultApiModel(
        adult = false,
        backdropPath = "/xyz789",
        genreIds = listOf(18, 36),
        id = 789012,
        originalLanguage = "fr",
        originalTitle = "Le Film Original",
        overview = "C'est un excellent film!",
        popularity = 67.89,
        posterPath = "/ghi789",
        releaseDate = "2023-02-01",
        title = "Le Film",
        video = false,
        voteAverage = 8.0,
        voteCount = 1500
    )
)

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