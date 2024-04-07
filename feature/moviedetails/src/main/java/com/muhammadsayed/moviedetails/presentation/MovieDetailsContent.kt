@file:OptIn(ExperimentalComposeUiApi::class)

package com.muhammadsayed.moviedetails.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.muhammadsayed.common.Response
import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.common.util.TestTag
import com.muhammadsayed.common.util.TestTag.DetailsList
import com.muhammadsayed.common.util.TestTag.DetailsMovieGenres
import com.muhammadsayed.common.util.TestTag.DetailsMovieImage
import com.muhammadsayed.design.components.AppChip
import com.muhammadsayed.design.components.CircularProgress
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
                LoadingPage(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(TestTag.DetailsLoading)
                )
            }

            is Response.Success -> MovieDetails(it.data) {
                onNavigateBack()
            }

            else -> {}
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetails(
    movie: MovieDetailsUiModel,
    onNavigateBack: () -> Unit
) {
    Box {

        LazyColumn(modifier = Modifier.semantics {
            testTagsAsResourceId = true
        }.testTag(DetailsList)) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(DetailsMovieImage)
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {

                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${Constants.IMAGE_BASE_URL}/w500/${movie.backDropPath}")
                            .size(Size.ORIGINAL) // Set the target size to load the image at.
                            .crossfade(true)
                            .build()
                    )
                    if (painter.state is AsyncImagePainter.State.Loading) {
                        CircularProgress(true)
                    } else {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDescription = "Movie Image",
                            contentScale = ContentScale.Crop
                        )
                    }

                }

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

                    FlowRow(modifier = Modifier.testTag(DetailsMovieGenres)) {
                        movie.genres.forEach { genre ->
                            AppChip(genre.name) {

                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = movie.overview,
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
                .background(shape = CircleShape, color = MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .clip(CircleShape)
                .clickable {
                    onNavigateBack()
                }
                .padding(5.dp)
                .align(Alignment.TopStart)
                .testTag(TestTag.BackIcon)

        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                "Back Button",
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}

