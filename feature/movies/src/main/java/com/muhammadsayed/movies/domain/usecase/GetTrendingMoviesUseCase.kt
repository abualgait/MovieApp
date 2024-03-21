package com.muhammadsayed.movies.domain.usecase

import androidx.paging.PagingData
import com.muhammadsayed.movies.data.model.Result
import com.muhammadsayed.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Result>> {
        return movieRepository.getTrendingMovies()
    }
}