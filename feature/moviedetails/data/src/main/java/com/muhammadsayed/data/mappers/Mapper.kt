package com.muhammadsayed.data.mappers

import com.muhammadsayed.data.model.MovieDetailsApiModel
import com.muhammadsayed.data.model.MovieDetailsGenreApiModel
import com.muhammadsayed.domain.model.MovieDetailsDomainModel
import com.muhammadsayed.domain.model.MovieDetailsDomainModel.Companion.DEFAULT_BACK_DROP_PATH
import com.muhammadsayed.domain.model.MovieDetailsDomainModel.Companion.DEFAULT_GENRES
import com.muhammadsayed.domain.model.MovieDetailsDomainModel.Companion.DEFAULT_OVERVIEW
import com.muhammadsayed.domain.model.MovieDetailsDomainModel.Companion.DEFAULT_RELEASE_DATE
import com.muhammadsayed.domain.model.MovieDetailsDomainModel.Companion.DEFAULT_TITLE
import com.muhammadsayed.domain.model.MovieDetailsGenreDomainModel
import com.muhammadsayed.domain.model.MovieDetailsGenreDomainModel.Companion.DEFAULT_NAME

fun MovieDetailsApiModel.toDomainModel(): MovieDetailsDomainModel? =
    if (this.id == null) {
        null
    } else {
        MovieDetailsDomainModel(
            id = id,
            backDropPath = backDropPath ?: DEFAULT_BACK_DROP_PATH,
            overview = overview ?: DEFAULT_OVERVIEW,
            releaseDate = releaseDate ?: DEFAULT_RELEASE_DATE,
            title = title ?: DEFAULT_TITLE,
            genres = genres?.mapNotNull { it.toDomainModel() } ?: DEFAULT_GENRES,
        )
    }

fun MovieDetailsGenreApiModel?.toDomainModel(): MovieDetailsGenreDomainModel? =
    if (this?.id == null) {
        // id is null, invalidating object as it is required
        null
    } else {
        MovieDetailsGenreDomainModel(
            id = id,
            name = name ?: DEFAULT_NAME,
        )
    }
