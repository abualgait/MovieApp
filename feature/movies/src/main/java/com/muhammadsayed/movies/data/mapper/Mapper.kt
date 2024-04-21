package com.muhammadsayed.movies.data.mapper

import com.muhammadsayed.movies.data.model.Result
import com.muhammadsayed.movies.domain.model.MovieDomainModel


fun Result.toMovieDomainModel(): MovieDomainModel {
    return MovieDomainModel(
        id = id,
        title = title,
        image = posterPath,
        year = releaseDate
    )
}