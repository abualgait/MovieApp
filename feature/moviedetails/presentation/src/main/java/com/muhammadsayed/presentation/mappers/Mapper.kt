package com.muhammadsayed.presentation.mappers

import com.muhammadsayed.domain.model.GenreDomainModel
import com.muhammadsayed.domain.model.MovieDetailsDomainModel
import com.muhammadsayed.presentation.GenreUiModel
import com.muhammadsayed.presentation.MovieDetailsUiModel

fun MovieDetailsDomainModel.toMovieDetailUiModel(): MovieDetailsUiModel {
    return MovieDetailsUiModel(
        backDropPath = backDropPath,
        id = id,
        originalLanguage = originalLanguage,
        overview = overview,
        releaseDate = releaseDate,
        title = title,
        status = status,
        genres = genres.map { it.toGenreUiModel() }
    )
}

fun GenreDomainModel.toGenreUiModel(): GenreUiModel {
    return GenreUiModel(
        id = id,
        name = name
    )
}