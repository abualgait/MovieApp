package com.muhammadsayed.movies.data.data_source

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.google.common.truth.Truth
import com.muhammadsayed.movies.data.data_soruce.MovieDataSource
import com.muhammadsayed.movies.data.remote.MoviesService
import com.muhammadsayed.movies.movieResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDataSourceTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private var apiService: MoviesService = mockk()

    private lateinit var sut: MovieDataSource

    @Before
    fun setup() {
        sut = MovieDataSource(apiService, testDispatcher)
    }

    @SuppressLint("CheckResult")
    @Test
    fun load_ReturnPageWithData() = runTest {

        coEvery { apiService.getListOfTrendingMovies() } returns movieResponse

        val params = PagingSource.LoadParams.Refresh(1, 10, false)

        val result = sut.load(params)

        Truth.assertThat(result is PagingSource.LoadResult.Page)
        val pageResult = result as PagingSource.LoadResult.Page
        Truth.assertThat(pageResult.data).isNotEmpty()
    }

    @Test
    fun loadReturnError() = runTest {

        coEvery { apiService.getListOfTrendingMovies() } throws Exception("Error")

        val params = PagingSource.LoadParams.Refresh(1, 10, false)

        val result = sut.load(params)

        Truth.assertThat(result is PagingSource.LoadResult.Error)
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }


}