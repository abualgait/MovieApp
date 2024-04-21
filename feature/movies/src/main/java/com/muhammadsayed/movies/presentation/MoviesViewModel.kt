package com.muhammadsayed.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.muhammadsayed.movies.data.mapper.toMovieDomainModel
import com.muhammadsayed.movies.domain.model.MovieDomainModel
import com.muhammadsayed.movies.domain.usecase.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<MovieDomainModel>>(PagingData.empty())
    val state: StateFlow<PagingData<MovieDomainModel>> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = PagingData.empty(),
    )

    init {
        onEvent(MoviesUiEvents.GetTrendingMovies)
    }

    private fun onEvent(event: MoviesUiEvents) {
        when (event) {
            MoviesUiEvents.GetTrendingMovies -> {
                getTrendingMovies()
            }
        }

    }

    private fun getTrendingMovies() {
        moviesUseCases.getTrendingMoviesUseCase().cachedIn(viewModelScope).onEach {
            _state.value = it.map { movie -> movie.toMovieDomainModel() }
        }.launchIn(viewModelScope)
    }

}