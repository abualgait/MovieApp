package com.muhammadsayed.data.mappers

import com.muhammadsayed.data.model.Genre
import com.muhammadsayed.data.model.MovieDetail
import com.muhammadsayed.domain.model.GenreDomainModel
import com.muhammadsayed.domain.model.MovieDetailsDomainModel

fun MovieDetail.toMovieDetailDomainModel(): MovieDetailsDomainModel {
    return MovieDetailsDomainModel(
        backDropPath = backDropPath,
        id = id,
        originalLanguage = originalLanguage,
        overview = overview,
        releaseDate = releaseDate,
        title = title,
        status = status,
        genres = genres.map { it.toGenreDetailDomainModel() }
    )
}

fun Genre.toGenreDetailDomainModel(): GenreDomainModel {
    return GenreDomainModel(
        id = id,
        name = name
    )
}