package com.muhammadsayed.yassirmovieapp.testdoubles

import androidx.paging.PagingData
import com.muhammadsayed.movies.data.mapper.toResultDomainModel
import com.muhammadsayed.movies.domain.model.ResultDomainModel
import com.muhammadsayed.movies.domain.repository.MovieRepository
import com.muhammadsayed.yassirmovieapp.resultList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMoviesRepository : MovieRepository {
    override fun getTrendingMovies(): Flow<PagingData<ResultDomainModel>> {
        return flowOf(PagingData.from(resultList.map { it.toResultDomainModel() }))
    }
}