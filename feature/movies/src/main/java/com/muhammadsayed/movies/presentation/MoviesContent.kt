package com.muhammadsayed.movies.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.muhammadsayed.common.extensions.getYearFromDate
import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.design.components.CircularProgress
import com.muhammadsayed.design.components.ErrorDialog
import com.muhammadsayed.design.components.LoadingPage
import com.muhammadsayed.design.theme.YassirMovieAppTheme
import com.muhammadsayed.movies.domain.model.MovieUIModel
import com.muhammadsayed.movies.extentions.rememberLazyListState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesContent(
    trendingPagingItem: LazyPagingItems<MovieUIModel>,
    onNavigateDetailScreen: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        trendingPagingItem.rememberLazyListState()
    ) {

        stickyHeader {
            Text(
                text = "Trending Movies",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold, modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            )
        }

        items(
            count = trendingPagingItem.itemCount,
            key = {
                trendingPagingItem[it]?.id ?: -1
            }) {
            trendingPagingItem[it]?.let { movie ->
                TrendingMovieItem(
                    movie = movie,
                    onNavigateDetailScreen = onNavigateDetailScreen
                )
            }
        }

        trendingPagingItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingPage(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = trendingPagingItem.loadState.refresh as LoadState.Error
                    item {
                        AnimatedVisibility(visible = showDialog) {
                            ErrorDialog(
                                errorMessage = error.error.localizedMessage!!,
                                onRetryClick = { retry() },
                                onDismiss = { showDialog = false }
                            )
                        }

                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgress(true)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TrendingMovieItem(
    movie: MovieUIModel,
    onNavigateDetailScreen: (String) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        .clickable {
            onNavigateDetailScreen(movie.id.toString())
        }) {
        Card(
            modifier = Modifier
                .width(100.dp)
                .height(150.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(1.dp),
        ) {
            if (LocalInspectionMode.current) {
                Image(
                    imageVector = Icons.Default.Favorite,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentDescription = "Trending Movie Image",
                    contentScale = ContentScale.Crop
                )
            } else {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${Constants.IMAGE_BASE_URL}/w300/${movie.image}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Trending Movie Image",
                    contentScale = ContentScale.Crop
                )
            }

        }

        Spacer(modifier = Modifier.size(10.dp))

        Column {
            Text(
                text = movie.title,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2
            )
            Text(
                text = movie.year.getYearFromDate() ?: "",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium
            )
        }
    }

}

@Preview
@Composable
private fun TrendingMovieItemPrev() {
    val movieUIModel = MovieUIModel(1, "Movie", "", "2024")
    YassirMovieAppTheme {
        TrendingMovieItem(movieUIModel) {

        }
    }
}
