package com.muhammadsayed.yassirmovieapp.feature

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.muhammadsayed.common.util.TestTag.BackIcon
import com.muhammadsayed.common.util.TestTag.DetailsList
import com.muhammadsayed.common.util.TestTag.DetailsMovieGenres
import com.muhammadsayed.common.util.TestTag.DetailsMovieImage
import com.muhammadsayed.common.util.TestTag.MoviesTrending
import com.muhammadsayed.design.theme.YassirMovieAppTheme
import com.muhammadsayed.movies.di.NetworkModule
import com.muhammadsayed.movies.di.RepositoryModule
import com.muhammadsayed.movies.di.UseCaseModule
import com.muhammadsayed.yassirmovieapp.MainActivity
import com.muhammadsayed.yassirmovieapp.MainApp
import com.muhammadsayed.yassirmovieapp.movieDetail
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.muhammadsayed.moviedetails.data.di.NetworkModule as MovieDetailsNetworkModule
import com.muhammadsayed.moviedetails.data.di.RepositoryModule as MovieDetailsRepositoryModule
import com.muhammadsayed.moviedetails.data.di.UseCaseModule as MovieDetailsUseCaseModule

@HiltAndroidTest
@UninstallModules(
    RepositoryModule::class,
    NetworkModule::class,
    UseCaseModule::class,
    MovieDetailsRepositoryModule::class,
    MovieDetailsNetworkModule::class,
    MovieDetailsUseCaseModule::class

)
class MovieDetailsScreensTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            YassirMovieAppTheme {
                MainApp()
            }
        }
    }


    @Test
    fun testTrendingMovies() {
        composeRule.onNode(hasText(loading)).assertIsDisplayed()
        composeRule.onNode(hasTestTag(MoviesTrending)).assertIsDisplayed()
        composeRule.onNodeWithTag(MoviesTrending).performScrollToNode(hasText(theMovie))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(MoviesTrending).performScrollToNode(hasText(leFilm))
            .assertIsDisplayed()
    }


    @Test
    fun testNavigationToMovieWithId123456Details() {
        val itemMatcher = hasText(theMovie)
        composeRule.onNodeWithTag(MoviesTrending)
            .performScrollToNode(itemMatcher)
            .onChildren()
            .filterToOne(itemMatcher)
            .performClick()


        composeRule.onNodeWithTag(BackIcon).assertIsDisplayed()


    }

    @Test
    fun testNavigationToMovieWithId123456DetailsAndDisplayInfo() {
        val itemMatcher = hasText(theMovie)

        composeRule.onNodeWithTag(MoviesTrending)
            .performScrollToNode(itemMatcher)
            .onChildren()
            .filterToOne(itemMatcher)
            .performClick()

        val itemImage = hasTestTag(DetailsMovieImage)


        composeRule.onNodeWithTag(DetailsList).assertIsDisplayed()

        composeRule.onNodeWithTag(DetailsList)
            .performScrollToNode(itemImage)
            .onChildren()
            .filterToOne(itemImage)
            .assertIsDisplayed()

        composeRule.onNodeWithTag(DetailsList)
            .performScrollToNode(hasText(movieDetail.title))
            .onChildren()
            .filterToOne(hasText(movieDetail.title))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(DetailsList)
            .performScrollToNode(hasText(movieDetail.overview))
            .onChildren()
            .filterToOne(hasText(movieDetail.overview))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(DetailsList)
            .performScrollToNode(hasText(movieDetail.releaseDate))
            .onChildren()
            .filterToOne(hasText(movieDetail.releaseDate))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(DetailsList)
            .performScrollToNode(hasTestTag(DetailsMovieGenres)).assertIsDisplayed()

    }


    @Test
    fun testNavigationToMovieWithId123456DetailsAndGoBack() {
        val itemMatcher = hasText(theMovie)

        composeRule.onNodeWithTag(MoviesTrending)
            .performScrollToNode(itemMatcher)
            .onChildren()
            .filterToOne(itemMatcher)
            .performClick()

        composeRule.onNodeWithTag(BackIcon).assertIsDisplayed()

        composeRule.onNodeWithTag(BackIcon).performClick()


        composeRule.onNodeWithTag(MoviesTrending)
            .performScrollToNode(hasText(theMovie))
            .assertIsDisplayed()


    }

    companion object {
        const val theMovie = "The Movie"
        const val leFilm = "Le Film"
        const val loading = "Loading"
    }
}