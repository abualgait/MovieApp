package com.muhammadsayed.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle(initialValue = null)

    state?.let {
        MovieDetailsContent(
        viewState = it,
        onNavigateBack = onNavigateBack,
        onRetry = viewModel::onRetry,
    )
    }
}
