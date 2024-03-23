package com.muhammadsayed.moviedetails.data.mappers

import com.muhammadsayed.moviedetails.data.model.MovieDetail
import com.muhammadsayed.moviedetails.domain.model.MovieDetailsUiModel

fun MovieDetail.toMovieDetailUi(): MovieDetailsUiModel {
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