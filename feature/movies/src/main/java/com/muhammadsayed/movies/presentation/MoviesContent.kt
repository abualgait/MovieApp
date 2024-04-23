@file:OptIn(ExperimentalComposeUiApi::class)

package com.muhammadsayed.movies.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.muhammadsayed.common.extensions.getYearFromDate
import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.common.util.TestTag
import com.muhammadsayed.design.components.CircularProgress
import com.muhammadsayed.design.components.ErrorDialog
import com.muhammadsayed.design.components.LoadingPage
import com.muhammadsayed.design.theme.YassirMovieAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun MoviesContent(
    trendingPagingItem: LazyPagingItems<ResultUiModel>, onNavigateDetailScreen: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }

    val listState = rememberLazyListState()

    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val scope = rememberCoroutineScope()

    val pullToRefreshState = rememberPullToRefreshState()

    var isRefreshing by remember {
        mutableStateOf(false)
    }
    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(Unit) {
            isRefreshing = true
            trendingPagingItem.refresh()
        }
    }
    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            pullToRefreshState.startRefresh()
        } else {
            pullToRefreshState.endRefresh()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
            .semantics {
                testTagsAsResourceId = true
            },
    ) {
        /**
         * workaround if condition, to save the scroll position when back from movie details.
         * alternatively - but it won't update listState firstVisibleItemIndex - use
         * trendingPagingItem.rememberLazyListState() [LazyPagingItems<T>.rememberLazyListState()] extension.
         * More info: https://issuetracker.google.com/issues/177245496.
         */
        if (trendingPagingItem.itemCount != 0) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(TestTag.MoviesTrending),
                state = listState
            ) {

                stickyHeader {
                    Text(
                        text = "Trending Movies \uD83D\uDD25",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp)
                    )
                }

                items(count = trendingPagingItem.itemCount, key = {
                    trendingPagingItem.peek(it)?.id ?: -1
                }) {
                    trendingPagingItem[it]?.let { movie ->
                        TrendingMovieItem(
                            modifier = Modifier.animateItemPlacement(),
                            movie = movie, onNavigateDetailScreen = onNavigateDetailScreen
                        )
                    }
                }

            }
        }

        trendingPagingItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingPage(modifier = Modifier.fillMaxSize())
                }

                loadState.refresh is LoadState.NotLoading -> {
                    isRefreshing = false
                }

                loadState.refresh is LoadState.Error -> {
                    pullToRefreshState.endRefresh()
                    val error = trendingPagingItem.loadState.refresh as LoadState.Error
                    AnimatedVisibility(visible = showDialog) {
                        ErrorDialog(errorMessage = error.error.localizedMessage!!,
                            onRetryClick = { retry() },
                            onDismiss = { showDialog = false })
                    }

                }

                loadState.append is LoadState.Loading -> {
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

        AnimatedVisibility(visible = showButton, modifier = Modifier.align(Alignment.BottomEnd)) {
            ScrollToTopButton {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        }
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter),
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
fun ScrollToTopButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.background
            )
            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .padding(10.dp)


    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            "Scroll to Top Button",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.rotate(90f)
        )
    }
}

@Composable
fun TrendingMovieItem(
    movie: ResultUiModel, modifier: Modifier = Modifier, onNavigateDetailScreen: (String) -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        .clickable {
            onNavigateDetailScreen(movie.id.toString())
        }
        .testTag(TestTag.MoviesClickRow)) {
        MovieImage(movie)
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

@Composable
fun MovieImage(movie: ResultUiModel) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(1.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (LocalInspectionMode.current) {
                Image(
                    imageVector = Icons.Default.Favorite,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Trending Movie Image",
                    contentScale = ContentScale.Crop
                )
            } else {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${Constants.IMAGE_BASE_URL}/w300/${movie.image}")
                        .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
                        .crossfade(true)
                        .build()
                )
                if (painter.state is AsyncImagePainter.State.Loading) {
                    CircularProgress(true)
                } else {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentDescription = "Trending Movie Image",
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
    }

}

@Preview
@Composable
private fun TrendingMovieItemPrev() {
    val movieUIModel = ResultUiModel(1, "Movie", "", "2024")
    YassirMovieAppTheme {
        TrendingMovieItem(movieUIModel) {

        }
    }
}
