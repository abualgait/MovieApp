package com.muhammadsayed.moviedetails.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.muhammadsayed.common.Response
import com.muhammadsayed.common.extensions.getYearFromDate
import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.design.components.ErrorDialog
import com.muhammadsayed.design.components.LoadingPage
import com.muhammadsayed.moviedetails.domain.model.MovieDetailsUiModel

@Composable
fun MovieDetailsContent(
    state: Response<MovieDetailsUiModel>,
    onNavigateBack: () -> Unit,
    onRetry: () -> Unit,
) {
    AnimatedContent(targetState = state, label = "Details Transition", transitionSpec = {
        fadeIn(
            animationSpec = tween(600),
        ) togetherWith fadeOut(
            animationSpec = tween(600)
        )
    }) {
        when (it) {
            is Response.Error -> {
                ErrorDialog(
                    errorMessage = it.exception.localizedMessage ?: "",
                    onRetryClick = { onRetry() }) {
                }
            }

            Response.Loading -> {
                LoadingPage(modifier = Modifier.fillMaxSize())
            }

            is Response.Success -> MovieDetails(it.data) {
                onNavigateBack()
            }
        }
    }
}


@Composable
fun MovieDetails(
    movie: MovieDetailsUiModel,
    onNavigateBack: () -> Unit
) {
    Box {


        LazyColumn(modifier = Modifier) {
            item {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${Constants.IMAGE_BASE_URL}/w500/${movie.backDropPath}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.size(10.dp))
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = movie.title,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = movie.releaseDate,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = movie.overview.getYearFromDate() ?: "",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(shape = CircleShape, color = Color.Black.copy(alpha = 0.1f))
                .clickable {
                    onNavigateBack()
                }
                .padding(5.dp)
                .align(Alignment.TopStart)

        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, "Back Button", tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}

