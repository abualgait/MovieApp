package com.muhammadsayed.movies

import com.muhammadsayed.movies.data.model.MovieApiModel
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

val movieResponse = MovieApiModel(
    page = 1,
    results = listOf(
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
    ),
    totalPages = 5,
    totalResults = 10
)