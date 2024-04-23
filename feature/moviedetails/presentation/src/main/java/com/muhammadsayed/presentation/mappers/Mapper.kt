package com.muhammadsayed.presentation.mappers

import com.muhammadsayed.domain.model.MovieDetailsDomainModel
import com.muhammadsayed.domain.model.MovieDetailsGenreDomainModel
import com.muhammadsayed.presentation.models.GenreUiModel
import com.muhammadsayed.presentation.models.MovieDetailsUiModel
import kotlinx.collections.immutable.toPersistentList

fun MovieDetailsDomainModel.toUiModel(): MovieDetailsUiModel {
    return MovieDetailsUiModel(
        backDropPath = backDropPath,
        id = id,
        overview = overview,
        releaseDate = releaseDate,
        title = title,
        genres = genres.map { it.toUiModel() }.toPersistentList(),
    )
}

fun MovieDetailsGenreDomainModel.toUiModel(): GenreUiModel {
    return GenreUiModel(
        id = id,
        name = name,
    )
}