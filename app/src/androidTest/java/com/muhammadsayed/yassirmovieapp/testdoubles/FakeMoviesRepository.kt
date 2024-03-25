package com.muhammadsayed.yassirmovieapp.testdoubles

import androidx.paging.PagingData
import com.muhammadsayed.movies.data.model.Result
import com.muhammadsayed.movies.domain.repository.MovieRepository
import com.muhammadsayed.yassirmovieapp.resultList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMoviesRepository : MovieRepository {
    override fun getTrendingMovies(): Flow<PagingData<Result>> {
        return flowOf(PagingData.from(resultList))
    }
}