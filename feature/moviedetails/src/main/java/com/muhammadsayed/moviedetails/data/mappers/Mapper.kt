package com.muhammadsayed.moviedetails.data.mappers

import com.muhammadsayed.moviedetails.data.model.MovieDetail
import com.muhammadsayed.moviedetails.domain.model.MovieDetailsDomainModel

fun MovieDetail.toMovieDetailDomainModel(): MovieDetailsDomainModel {
    return MovieDetailsDomainModel(
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