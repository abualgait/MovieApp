package com.muhammadsayed.moviedetails.domain.model


data class MovieDetailsUiModel(
    val backDropPath: String,
    val status: String,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
)

