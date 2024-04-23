package com.muhammadsayed.domain.usecase

import com.muhammadsayed.common.Response
import com.muhammadsayed.domain.model.MovieDetailsDomainModel
import com.muhammadsayed.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetMovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(movieId: Int): Flow<Response<MovieDetailsDomainModel>> {
        return movieDetailsRepository.getMovieDetailsById(movieId).flowOn(coroutineDispatcher)
    }
}