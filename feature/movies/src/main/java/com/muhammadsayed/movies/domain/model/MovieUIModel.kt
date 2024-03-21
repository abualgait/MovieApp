package com.muhammadsayed.movies.domain.model

import javax.annotation.concurrent.Immutable

@Immutable
data class MovieUIModel(
    val id: Int,
    val title: String,
    val image: String,
    val year: String,
)
