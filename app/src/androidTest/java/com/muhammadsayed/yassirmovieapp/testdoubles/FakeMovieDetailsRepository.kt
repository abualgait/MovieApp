package com.muhammadsayed.yassirmovieapp.testdoubles

import com.muhammadsayed.common.Response
import com.muhammadsayed.data.mappers.toDomainModel
import com.muhammadsayed.domain.model.MovieDetailsDomainModel
import com.muhammadsayed.domain.repository.MovieDetailsRepository
import com.muhammadsayed.yassirmovieapp.movieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMovieDetailsRepository : MovieDetailsRepository {
    override suspend fun getMovieDetailsById(movieId: Int): Flow<Response<MovieDetailsDomainModel>> {
        return flowOf(Response.Success(movieDetail.toDomainModel()))
    }
}