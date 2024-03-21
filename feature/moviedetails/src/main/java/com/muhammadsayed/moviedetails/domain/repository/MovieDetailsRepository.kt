package com.muhammadsayed.moviedetails.domain.repository

import com.muhammadsayed.common.Response
import com.muhammadsayed.moviedetails.domain.model.MovieDetailsUiModel
import kotlinx.coroutines.flow.Flow


interface MovieDetailsRepository {
    suspend fun getMovieDetailsById(movieId: Int): Flow<Response<MovieDetailsUiModel>>
}