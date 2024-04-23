package com.muhammadsayed.movies.data.mapper

import com.muhammadsayed.movies.data.model.ResultApiModel
import com.muhammadsayed.movies.domain.model.ResultDomainModel


fun ResultApiModel.toResultDomainModel(): ResultDomainModel {
    return ResultDomainModel(
        id = id,
        title = title,
        image = posterPath,
        year = releaseDate
    )
}