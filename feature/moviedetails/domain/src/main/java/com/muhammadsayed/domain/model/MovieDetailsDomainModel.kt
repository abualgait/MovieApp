package com.muhammadsayed.domain.model

data class MovieDetailsDomainModel(
    val backDropPath: String,
    val status: String,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val genres: List<GenreDomainModel>,
)

