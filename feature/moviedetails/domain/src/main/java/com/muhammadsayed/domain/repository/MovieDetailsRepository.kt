package com.muhammadsayed.domain.repository

import com.muhammadsayed.common.Response
import com.muhammadsayed.domain.model.MovieDetailsDomainModel
import kotlinx.coroutines.flow.Flow


interface MovieDetailsRepository {
    suspend fun getMovieDetailsById(movieId: Int): Flow<Response<MovieDetailsDomainModel>>
}