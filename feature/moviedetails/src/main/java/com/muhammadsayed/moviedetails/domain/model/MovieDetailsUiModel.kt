package com.muhammadsayed.moviedetails.domain.model

import com.muhammadsayed.moviedetails.data.model.Genre


data class MovieDetailsUiModel(
    val backDropPath: String,
    val status: String,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val genres: List<Genre>,
)

