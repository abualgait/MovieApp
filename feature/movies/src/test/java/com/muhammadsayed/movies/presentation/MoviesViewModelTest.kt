package com.muhammadsayed.movies.presentation

import android.util.Log
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.muhammadsayed.movies.MainDispatcherRule
import com.muhammadsayed.movies.data.mapper.toResultDomainModel
import com.muhammadsayed.movies.data.repository.MovieRepositoryImpl
import com.muhammadsayed.movies.domain.usecase.GetTrendingMoviesUseCase
import com.muhammadsayed.movies.domain.usecase.MoviesUseCases
import com.muhammadsayed.movies.presentation.mapper.toResultUiModel
import com.muhammadsayed.movies.resultList
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule(order = 1)
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var sut: MoviesViewModel

    private var moviesUseCases: MoviesUseCases = mockk()

    private var mockMovieRepository: MovieRepositoryImpl = mockk()


    @Before
    fun setUp() {

        val pagingData = PagingData.from(resultList)


        every { moviesUseCases.getTrendingMoviesUseCase() } returns flowOf(pagingData.map { it.toResultDomainModel() })

        mockkStatic(Log::class)
        every { Log.isLoggable(any(), any()) } returns false
        every { mockMovieRepository.getTrendingMovies() } returns flowOf(pagingData.map { it.toResultDomainModel() })
        every {
            moviesUseCases.getTrendingMoviesUseCase
        } returns GetTrendingMoviesUseCase(mockMovieRepository)

        sut = MoviesViewModel(moviesUseCases)

    }


    @Test
    fun `test initial state is empty`() = runTest {

        sut.state.test {
            val item = awaitItem()
            val empty = PagingData.empty<ResultUiModel>()
            item.map {
                assertThat(it).isEqualTo(empty.map { model -> model })
            }
        }

    }


    @Test
    fun testGetTrendingMovies_ReturnPagingData() = runTest {

        val listUIModel = resultList.map { it.toResultDomainModel().toResultUiModel() }
        val differ = AsyncPagingDataDiffer(
            diffCallback = TestDiffCallback<ResultUiModel>(),
            updateCallback = TestListCallback(),
            workerDispatcher = StandardTestDispatcher()
        )

        sut.state.test {
            val item = awaitItem()
            differ.submitData(item)
            advanceUntilIdle()
            assertThat(listUIModel).isEqualTo(differ.snapshot().items)
        }

    }


    @After
    fun tearDown() {
        clearAllMocks()
    }

}


class TestListCallback : ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}

class TestDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}