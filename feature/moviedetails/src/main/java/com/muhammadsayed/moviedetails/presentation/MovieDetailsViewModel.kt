package com.muhammadsayed.moviedetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadsayed.common.Response
import com.muhammadsayed.common.util.Constants.MOVIE_ID
import com.muhammadsayed.moviedetails.domain.model.MovieDetailsDomainModel
import com.muhammadsayed.moviedetails.domain.usecase.MovieDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCases: MovieDetailsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<Response<MovieDetailsDomainModel>>(Response.Loading)
    val state: StateFlow<Response<MovieDetailsDomainModel>> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Response.Loading,
    )

    init {
        savedStateHandle.get<Int>(MOVIE_ID)?.let {
            onEvent(MovieDetailsEvents.GetMovieDetails(it))
        }

    }

    fun onEvent(event: MovieDetailsEvents) {
        when (event) {
            is MovieDetailsEvents.GetMovieDetails -> {
                viewModelScope.launch {
                    getTrendingMovies(event.movieID)
                }

            }
        }

    }

    private suspend fun getTrendingMovies(movieId: Int) {
        movieDetailsUseCases.getMovieDetailsUseCase(movieId).onEach {
            _state.value = it
        }.launchIn(viewModelScope)
    }

}

