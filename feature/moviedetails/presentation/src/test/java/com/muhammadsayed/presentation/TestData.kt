package com.muhammadsayed.presentation

import com.muhammadsayed.data.model.MovieDetailsApiModel

val movieDetail = MovieDetailsApiModel(
    adult = false,
    backDropPath = "/abc123",
    belongsToCollection = null,
    budget = 10000000,
    genres = listOf(
        MovieDetailsGenreApiModel(id = 1, name = "Action"),
        MovieDetailsGenreApiModel(id = 2, name = "Adventure")
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
