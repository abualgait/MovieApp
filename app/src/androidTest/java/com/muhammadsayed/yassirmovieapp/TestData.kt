package com.muhammadsayed.yassirmovieapp

import com.muhammadsayed.moviedetails.data.model.Genre
import com.muhammadsayed.moviedetails.data.model.MovieDetail
import com.muhammadsayed.moviedetails.data.model.ProductionCompany
import com.muhammadsayed.moviedetails.data.model.ProductionCountry
import com.muhammadsayed.moviedetails.data.model.SpokenLanguage
import com.muhammadsayed.movies.data.model.Result

val resultList = listOf(
    Result(
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
    Result(
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

val movieDetail = MovieDetail(
    adult = false,
    backDropPath = "/abc123",
    belongsToCollection = null,
    budget = 10000000,
    genres = listOf(
        Genre(id = 1, name = "Action"),
        Genre(id = 2, name = "Adventure")
    ),
    homepage = "https://www.example.com",
    id = 123456,
    imdbId = "tt1234567",
    originalLanguage = "en",
    originalTitle = "The Original Movie",
    overview = "This is a detailed overview of the movie.",
    popularity = 67.89,
    posterPath = "/def456",
    productionCompanies = listOf(
        ProductionCompany(
            id = 1,
            logoPath = "/logo1.png",
            name = "Production Company A",
            originCountry = "US"
        ),
        ProductionCompany(
            id = 2,
            logoPath = "/logo2.png",
            name = "Production Company B",
            originCountry = "CA"
        )
    ),
    productionCountries = listOf(
        ProductionCountry(
            iso = "US",
            name = "United States"
        ),
        ProductionCountry(
            iso = "CA",
            name = "Canada"
        )
    ),
    releaseDate = "2023-01-01",
    revenue = 50000000,
    runtime = 120,
    spokenLanguages = listOf(
        SpokenLanguage(
            englishName = "English",
            iso = "en",
            name = "English"
        ),
        SpokenLanguage(
            englishName = "French",
            iso = "fr",
            name = "Français"
        )
    ),
    status = "Released",
    tagline = "Tagline of the movie",
    title = "The Movie",
    video = false,
    voteAverage = 7.5,
    voteCount = 1000
)