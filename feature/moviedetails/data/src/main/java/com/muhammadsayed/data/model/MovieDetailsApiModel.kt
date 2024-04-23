package com.muhammadsayed.data.model

import com.squareup.moshi.Json


data class MovieDetailsApiModel(
    val id: Int? = null,
    @Json(name = "backdrop_path")
    val backDropPath: String? = null,
    val genres: List<MovieDetailsGenreApiModel>? = null,
    val overview: String? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    val title: String? = null,
)

data class MovieDetailsGenreApiModel(
    val id: Int? = null,
    val name: String? = null,
)