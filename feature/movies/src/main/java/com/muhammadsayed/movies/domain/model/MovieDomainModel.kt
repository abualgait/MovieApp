package com.muhammadsayed.movies.domain.model

import javax.annotation.concurrent.Immutable

@Immutable
data class MovieDomainModel(
    val id: Int? = -1,
    val title: String? = "",
    val image: String? = "",
    val year: String? = "",
)
