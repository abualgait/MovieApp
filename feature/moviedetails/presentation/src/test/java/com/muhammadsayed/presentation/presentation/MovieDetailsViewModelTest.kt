package com.muhammadsayed.presentation.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.muhammadsayed.common.Response
import com.muhammadsayed.data.mappers.toDomainModel
import com.muhammadsayed.data.repository.MovieDetailsRepositoryImpl
import com.muhammadsayed.domain.usecase.GetMovieDetailsUseCase
import com.muhammadsayed.domain.usecase.MovieDetailsUseCases
import com.muhammadsayed.presentation.MainDispatcherRule
import com.muhammadsayed.presentation.MovieDetailsViewModel
import com.muhammadsayed.presentation.movieDetail
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    private lateinit var sut: MovieDetailsViewModel

    private var useCases: MovieDetailsUseCases = mockk()

    private var movieDetailsRepository: MovieDetailsRepositoryImpl = mockk()

    val MOVIE_ID = 1

    val savedStateHandle: SavedStateHandle = SavedStateHandle()

    @Before
    fun setUp() {

        coEvery {
            useCases.getMovieDetailsUseCase
        } returns GetMovieDetailsUseCase(movieDetailsRepository, StandardTestDispatcher())

        sut = MovieDetailsViewModel(useCases, savedStateHandle)

    }


    @Test
    fun `test initial state is Loading`() = runTest {
        sut.viewState.test {
            val item = awaitItem()
            assertThat(item).isEqualTo(Response.Loading)
        }
    }

    @Test
    fun `test calling onEvent MovieDetailsEvents -  state is Success`() = runTest {

        coEvery {
            useCases.getMovieDetailsUseCase(MOVIE_ID)
        } returns flow {
            emit(Response.Loading)
            emit(Response.Success(movieDetail.toDomainModel()))
        }

        sut.onEvent(MovieDetailsEvents.GetMovieDetails(MOVIE_ID))

        sut.viewState.test {

            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(Response.Loading)

            assertThat(awaitItem()).isEqualTo(Response.Success(movieDetail.toDomainModel()))


        }
    }

    @Test
    fun `test calling onEvent MovieDetailsEvents -  state is Failed`() = runTest {

        val exception = Exception("Error")
        coEvery {
            useCases.getMovieDetailsUseCase(MOVIE_ID)
        } returns flow {
            emit(Response.Loading)
            emit(Response.Error(exception))
        }

        sut.onEvent(MovieDetailsEvents.GetMovieDetails(MOVIE_ID))

        sut.viewState.test {

            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(Response.Loading)

            assertThat(awaitItem()).isEqualTo(Response.Error(exception))
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }

}

