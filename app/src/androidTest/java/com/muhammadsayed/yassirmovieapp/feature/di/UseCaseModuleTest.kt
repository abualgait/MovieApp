package com.muhammadsayed.yassirmovieapp.feature.di

import com.muhammadsayed.moviedetails.domain.usecase.GetMovieDetailsUseCase
import com.muhammadsayed.moviedetails.domain.usecase.MovieDetailsUseCases
import com.muhammadsayed.movies.domain.usecase.GetTrendingMoviesUseCase
import com.muhammadsayed.movies.domain.usecase.MoviesUseCases
import com.muhammadsayed.yassirmovieapp.testdoubles.FakeMovieDetailsRepository
import com.muhammadsayed.yassirmovieapp.testdoubles.FakeMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModuleTest {
    @Provides
    @Singleton
    fun provideMoviesUseCases(
    ): MoviesUseCases {
        return MoviesUseCases(
            getTrendingMoviesUseCase = GetTrendingMoviesUseCase(
                movieRepository = FakeMoviesRepository()
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @Singleton
    fun provideMovieDetailsUseCases(
    ): MovieDetailsUseCases {
        return MovieDetailsUseCases(
            getMovieDetailsUseCase = GetMovieDetailsUseCase(
                movieDetailsRepository = FakeMovieDetailsRepository(),
                coroutineDispatcher = UnconfinedTestDispatcher()
            )
        )
    }
}


