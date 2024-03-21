package com.muhammadsayed.moviedetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammadsayed.common.Response
import com.muhammadsayed.moviedetails.domain.model.MovieDetailsUiModel
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
    private val movieDetailsUseCases: MovieDetailsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<Response<MovieDetailsUiModel>>(Response.Loading)
    val state: StateFlow<Response<MovieDetailsUiModel>> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Response.Loading,
    )

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

