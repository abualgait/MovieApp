package com.muhammadsayed.data.repository

import com.muhammadsayed.common.Response
import com.muhammadsayed.data.mappers.toDomainModel
import com.muhammadsayed.data.remote.MoviesDetailsService
import com.muhammadsayed.domain.errors.InvalidDataErrorDomainModel
import com.muhammadsayed.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieDetailsRepositoryImpl(
    private val detailApiService: MoviesDetailsService,
) : MovieDetailsRepository {
    override suspend fun getMovieDetailsById(movieId: Int) = flow {
        try {
            emit(Response.Loading)
            val result = detailApiService.getMovieDetails(
                movieId = movieId
            ).toDomainModel()
            if (result != null) {
                emit(Response.Success(result))
            } else {
                emit(Response.Error(InvalidDataErrorDomainModel()))
            }

        } catch (e: Exception) {
            emit(Response.Error(e))
        } catch (e: HttpException) {
            emit(Response.Error(e))
        }

    }
}
