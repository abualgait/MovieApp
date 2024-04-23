package com.muhammadsayed.moviedetails.presentation.mappers

import com.muhammadsayed.moviedetails.domain.model.MovieDetailsDomainModel
import com.muhammadsayed.moviedetails.presentation.MovieDetailsUiModel

fun MovieDetailsDomainModel.toMovieDetailUiModel(): MovieDetailsUiModel {
    return MovieDetailsUiModel(
        backDropPath = backDropPath,
        id = id,
        originalLanguage = originalLanguage,
        overview = overview,
        releaseDate = releaseDate,
        title = title,
        status = status,
        genres = genres
    )
}