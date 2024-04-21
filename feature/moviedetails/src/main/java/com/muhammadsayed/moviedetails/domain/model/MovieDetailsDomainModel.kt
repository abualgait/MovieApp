package com.muhammadsayed.moviedetails.domain.model

import com.muhammadsayed.moviedetails.data.model.Genre


data class MovieDetailsDomainModel(
    val backDropPath: String? = "",
    val status: String? = "",
    val id: Int? = -1,
    val originalLanguage: String? = "",
    val overview: String? = "",
    val releaseDate: String? = "",
    val title: String? = "",
    val genres: List<Genre>? = emptyList(),
)

