package com.muhammadsayed.presentation.domain

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.muhammadsayed.common.Response
import com.muhammadsayed.data.mappers.toDomainModel
import com.muhammadsayed.data.repository.MovieDetailsRepositoryImpl
import com.muhammadsayed.domain.usecase.GetMovieDetailsUseCase
import com.muhammadsayed.presentation.movieDetail
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: GetMovieDetailsUseCase

    private var mockMovieRepository: MovieDetailsRepositoryImpl = mockk()

    val MOVIE_ID = 1

    @Before
    fun setUp() {
        sut = GetMovieDetailsUseCase(mockMovieRepository, UnconfinedTestDispatcher())
    }

    @Test
    fun callGetMovieDetailsUseCase_ReturnSuccess() = runTest {
        coEvery {
            mockMovieRepository.getMovieDetailsById(MOVIE_ID)
        } returns flowOf(Response.Loading, Response.Success(movieDetail.toDomainModel()!!))

        val result = sut(MOVIE_ID)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem)
                .isEqualTo(Response.Success(movieDetail.toDomainModel()))
            awaitComplete()
        }
    }

    @Test
    fun callGetMovieDetailsUseCase_ReturnException() = runTest {
        val exception = Exception("Error")
        coEvery {
            mockMovieRepository.getMovieDetailsById(MOVIE_ID)
        } returns flowOf(Response.Loading, Response.Error(exception))

        val result = sut(MOVIE_ID)
        result.test {
            val firstItem = awaitItem()
            Truth.assertThat(firstItem).isEqualTo(Response.Loading)

            val secondItem = awaitItem()
            Truth.assertThat(secondItem).isEqualTo(Response.Error(exception))
            awaitComplete()
        }
    }

    @Test
    fun callGetMovieDetailsUseCase_ReturnHttpException() = runTest {
        val mockHttpException = HttpException(
            retrofit2.Response.error<String>(
                404,
                "Not Found".toResponseBody("text/plain".toMediaTypeOrNull())
            )
        )
        coEvery {
            mockMovieRepository.getMovieDetailsById(MOVIE_ID)
        } returns flowOf(Response.Loading, Response.Error(mockHttpException))

        val result = sut(MOVIE_ID)
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