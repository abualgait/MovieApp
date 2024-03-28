package com.muhammadsayed.movies.data.data_soruce

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muhammadsayed.movies.data.model.Result
import com.muhammadsayed.movies.data.remote.MoviesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieDataSource(
    private val apiService: MoviesService,
    private val defaultDispatcher: CoroutineDispatcher,
) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        val prevKey = if (page == 1) null else page - 1
        val nextKey = page + 1
        return try {
            withContext(defaultDispatcher) {
                val response = apiService.getListOfTrendingMovies(page = page)
                LoadResult.Page(
                    data = response.results,
                    nextKey = nextKey,
                    prevKey = prevKey
                )
            }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }
}