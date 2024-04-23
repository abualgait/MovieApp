package com.muhammadsayed.movies.data.data_soruce

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muhammadsayed.movies.data.mapper.toResultDomainModel
import com.muhammadsayed.movies.data.remote.MoviesService
import com.muhammadsayed.movies.domain.model.ResultDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieDataSource(
    private val apiService: MoviesService,
    private val coroutineDispatcher: CoroutineDispatcher,
) : PagingSource<Int, ResultDomainModel>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDomainModel> {
        val page = params.key ?: 1
        val prevKey = if (page == 1) null else page - 1
        val nextKey = page + 1
        return try {
            withContext(coroutineDispatcher) {
                val response = apiService.getListOfTrendingMovies(page = page)
                LoadResult.Page(
                    data = response.results.map { it.toResultDomainModel() },
                    nextKey = nextKey,
                    prevKey = prevKey
                )
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultDomainModel>): Int? {
        return null
    }
}