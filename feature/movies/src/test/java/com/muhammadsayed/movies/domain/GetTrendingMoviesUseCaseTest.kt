package com.muhammadsayed.movies.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.muhammadsayed.movies.domain.repository.MovieRepository
import com.muhammadsayed.movies.domain.usecase.GetTrendingMoviesUseCase
import com.muhammadsayed.movies.resultList
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetTrendingMoviesUseCaseTest {
    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: GetTrendingMoviesUseCase

    private var mockMovieRepository: MovieRepository = mockk()

    @Before
    fun setUp() {
        sut = GetTrendingMoviesUseCase(mockMovieRepository)
    }

    @Test
    fun callGetTrendingMoviesUseCase_ReturnCorrectPagingData() = runTest {
        val pagingData = PagingData.from(resultList)

        every {
            mockMovieRepository.getTrendingMovies()
        } returns flowOf(pagingData)

        val result = sut()
        result.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(pagingData)
            awaitComplete()
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }


}