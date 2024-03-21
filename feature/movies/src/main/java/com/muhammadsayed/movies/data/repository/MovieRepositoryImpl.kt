package com.muhammadsayed.movies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.movies.data.data_soruce.MovieDataSource
import com.muhammadsayed.movies.data.model.Result
import com.muhammadsayed.movies.data.remote.MoviesService
import com.muhammadsayed.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val apiService: MoviesService) : MovieRepository {
    override fun getTrendingMovies(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                MovieDataSource(
                    apiService = apiService,
                )
            },
        ).flow
    }
}
