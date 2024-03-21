package com.muhammadsayed.movies.domain.repository

import androidx.paging.PagingData
import com.muhammadsayed.movies.data.model.Result
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun getTrendingMovies(): Flow<PagingData<Result>>
}