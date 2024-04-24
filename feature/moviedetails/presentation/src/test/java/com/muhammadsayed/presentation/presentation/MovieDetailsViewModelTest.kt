package com.muhammadsayed.presentation.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.muhammadsayed.common.Response
import com.muhammadsayed.common.util.Constants.MOVIE_ID
import com.muhammadsayed.data.mappers.toDomainModel
import com.muhammadsayed.data.repository.MovieDetailsRepositoryImpl
import com.muhammadsayed.domain.usecase.GetMovieDetailsUseCase
import com.muhammadsayed.domain.usecase.MovieDetailsUseCases
import com.muhammadsayed.presentation.MainDispatcherRule
import com.muhammadsayed.presentation.MovieDetailsViewModel
import com.muhammadsayed.presentation.mappers.toUiModel
import com.muhammadsayed.presentation.models.MovieDetailsViewStateUiModel
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

    private val movieId = 1

    private val savedStateHandle: SavedStateHandle = SavedStateHandle(mapOf(MOVIE_ID to movieId))

    @Before
    fun setUp() {

        coEvery {
            useCases.getMovieDetailsUseCase
        } returns GetMovieDetailsUseCase(movieDetailsRepository, StandardTestDispatcher())

        sut = MovieDetailsViewModel(useCases, savedStateHandle)

    }


    @Test
    fun `test initial state is Loading`() = runTest {
        coEvery {
            useCases.getMovieDetailsUseCase(movieId)
        } returns flow {
            emit(Response.Loading)
        }
        sut.viewState.test {
            val item = awaitItem()
            assertThat(item).isEqualTo(MovieDetailsViewStateUiModel.Loading)
        }
    }

    @Test
    fun `test calling onEvent MovieDetailsEvents -  state is Success`() = runTest {
        val domainModel = movieDetail.toDomainModel() ?: return@runTest
        coEvery {
            useCases.getMovieDetailsUseCase(movieId)
        } returns flow {
            emit(Response.Loading)
            emit(Response.Success(domainModel))
        }

        sut.viewState.test {

            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(MovieDetailsViewStateUiModel.Loading)

            assertThat(awaitItem()).isEqualTo(
                MovieDetailsViewStateUiModel.Success(
                    movie = domainModel.toUiModel()
                )
            )
        }
    }

    @Test
    fun `test calling onEvent MovieDetailsEvents -  state is Failed`() = runTest {

        val exception = Exception("Error")
        coEvery {
            useCases.getMovieDetailsUseCase(movieId)
        } returns flow {
            emit(Response.Loading)
            emit(Response.Error(exception))
        }

        sut.viewState.test {

            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(MovieDetailsViewStateUiModel.Loading)

            assertThat(awaitItem()).isEqualTo(
                MovieDetailsViewStateUiModel.Error(
                    message = exception.localizedMessage ?: "Something went wrong",
                    movieId = movieId,
                )
            )
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }

}

