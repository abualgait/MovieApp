package com.muhammadsayed.movies.data.repository

import androidx.paging.map
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammadsayed.movies.data.remote.MoviesService
import com.muhammadsayed.movies.domain.repository.MovieRepository
import com.muhammadsayed.movies.movieResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieRepositoryTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: MovieRepository

    private val apiService: MoviesService = mockk()

    private val defaultDispatcher: CoroutineDispatcher = mockk()

    @Before
    fun setUp() {
        sut = MovieRepositoryImpl(apiService, defaultDispatcher)
    }

    @Test
    fun callGetTrendingMovies_ReturnCorrectPagingData() = runTest {

        coEvery {
            apiService.getListOfTrendingMovies()
        } returns movieResponse

        val result = sut.getTrendingMovies()
        result.test {
            val firstItem = awaitItem()
            firstItem.map {
                Truth.assertThat(it).isEqualTo(movieResponse.results)
                awaitComplete()
            }

        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }

}