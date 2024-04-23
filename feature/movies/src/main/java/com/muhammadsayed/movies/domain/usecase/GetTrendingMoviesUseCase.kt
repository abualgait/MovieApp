package com.muhammadsayed.movies.domain.usecase

import androidx.paging.PagingData
import com.muhammadsayed.movies.domain.model.ResultDomainModel
import com.muhammadsayed.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<ResultDomainModel>> {
        return movieRepository.getTrendingMovies()
    }
}