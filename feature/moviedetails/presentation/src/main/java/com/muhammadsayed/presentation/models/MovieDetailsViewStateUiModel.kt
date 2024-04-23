package com.muhammadsayed.presentation.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface MovieDetailsViewStateUiModel {
    data object Loading : MovieDetailsViewStateUiModel

    @Immutable
    data class Success(val movie: MovieDetailsUiModel) : MovieDetailsViewStateUiModel

    @Immutable
    data class Error(val message: String, val movieId: Int) : MovieDetailsViewStateUiModel
}