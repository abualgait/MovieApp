package com.muhammadsayed.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadsayed.common.Response
import com.muhammadsayed.common.util.Constants.MOVIE_ID
import com.muhammadsayed.domain.usecase.MovieDetailsUseCases
import com.muhammadsayed.presentation.mappers.toUiModel
import com.muhammadsayed.presentation.models.MovieDetailsViewStateUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCases: MovieDetailsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId =
        MutableSharedFlow<Int?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: SharedFlow<MovieDetailsViewStateUiModel> =
        movieId.filterNotNull().flatMapLatest { movieId ->
            movieDetailsUseCases.getMovieDetailsUseCase(movieId).mapLatest {
                movieId to it
            }
        }.mapLatest { (movieId, response) ->
            when (response) {
                is Response.Loading -> MovieDetailsViewStateUiModel.Loading
                is Response.Error -> MovieDetailsViewStateUiModel.Error(
                    message = response.exception.localizedMessage ?: "Something went wrong",
                    movieId = movieId,
                )

                is Response.Success -> MovieDetailsViewStateUiModel.Success(
                    movie = response.data.toUiModel()
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = MovieDetailsViewStateUiModel.Loading,
        )


    init {
        savedStateHandle.get<Int>(MOVIE_ID)?.let {
            viewModelScope.launch(Dispatchers.IO) {
                movieId.emit(it)
            }
        }
    }

    fun onRetry(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieId.emit(id)
        }
    }
}
