package com.muhammadsayed.moviedetails.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammadsayed.common.Response
import com.muhammadsayed.moviedetails.MainDispatcherRule
import com.muhammadsayed.moviedetails.data.mappers.toMovieDetailUi
import com.muhammadsayed.moviedetails.data.remote.MoviesDetailsService
import com.muhammadsayed.moviedetails.domain.repository.MovieDetailsRepository
import com.muhammadsayed.moviedetails.movieDetail
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class MovieDetailsRepositoryTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var sut: MovieDetailsRepository

    private val apiService: MoviesDetailsService = mockk()

    val MOVIE_ID = 1

    @Before
    fun setUp() {
        sut = MovieDetailsRepositoryImpl(apiService)
    }

    @Test
    fun callGetMovieDetailsById_ReturnSuccess() = runTest {

        coEvery {
            apiService.getMovieDetails(MOVIE_ID)
        } returns movieDetail

        val result = sut.getMovieDetailsById(MOVIE_ID)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Success(movieDetail.toMovieDetailUi()))
            awaitComplete()
        }
    }


    @Test
    fun callGetMovieDetailsById_ReturnException() = runTest {
        val exception = Exception("Error")
        coEvery {
            apiService.getMovieDetails(MOVIE_ID)
        } throws exception

        val result = sut.getMovieDetailsById(MOVIE_ID)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Error(exception))
            awaitComplete()
        }
    }


    @Test
    fun callGetMovieDetailsById_ReturnHttpException() = runTest {
        val mockHttpException = HttpException(
            retrofit2.Response.error<String>(
                404,
                "Not Found".toResponseBody("text/plain".toMediaTypeOrNull())
            )
        )

        coEvery {
            apiService.getMovieDetails(MOVIE_ID)
        } throws mockHttpException

        val result = sut.getMovieDetailsById(MOVIE_ID)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Error(mockHttpException))
            awaitComplete()
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }


}