package com.muhammadsayed.movies.data.mapper

import com.muhammadsayed.movies.data.model.Result
import com.muhammadsayed.movies.domain.model.MovieUIModel


fun Result.toMovieUiModel(): MovieUIModel {
    return MovieUIModel(
        id = id,
        title = title,
        image = posterPath,
        year = releaseDate
    )
}