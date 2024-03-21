package com.muhammadsayed.moviedetails.data.repository

import com.muhammadsayed.common.Response
import com.muhammadsayed.moviedetails.data.mappers.toMovieDetailUi
import com.muhammadsayed.moviedetails.data.remote.MoviesDetailsService
import com.muhammadsayed.moviedetails.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieDetailsRepositoryImpl(
    private val detailApiService: MoviesDetailsService,
) : MovieDetailsRepository {
    override suspend fun getMovieDetailsById(movieId: Int) = flow {

        return@flow try {
            emit(Response.Loading)
            val result = detailApiService.getMovieDetails(movieId = movieId).toMovieDetailUi()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e))
        } catch (e: HttpException) {
            emit(Response.Error(e))
        }

    }

}