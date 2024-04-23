package com.muhammadsayed.movies.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieApiModel(
    val page: Int = -1,
    val results: List<ResultApiModel> = emptyList(),
    @Json(name = "total_pages")
    val totalPages: Int = -1,
    @Json(name = "total_results")
    val totalResults: Int = -1
)