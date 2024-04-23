package com.muhammadsayed.presentation.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList

@Immutable
data class MovieDetailsUiModel(
    val id: Int,
    val title: String,
    val backDropPath: String,
    val overview: String,
    val releaseDate: String,
    val genres: PersistentList<GenreUiModel>,
)

@Immutable
data class GenreUiModel(
    val id: Int,
    val name: String
)