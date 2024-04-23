package com.muhammadsayed.movies.presentation.mapper

import com.muhammadsayed.movies.domain.model.ResultDomainModel
import com.muhammadsayed.movies.presentation.ResultUiModel


fun ResultDomainModel.toResultUiModel(): ResultUiModel {
    return ResultUiModel(
        id = id,
        title = title,
        image = image,
        year = year
    )
}