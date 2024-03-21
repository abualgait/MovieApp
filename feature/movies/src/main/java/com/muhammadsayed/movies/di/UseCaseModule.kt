package com.muhammadsayed.movies.di

import com.muhammadsayed.movies.domain.repository.MovieRepository
import com.muhammadsayed.movies.domain.usecase.GetTrendingMoviesUseCase
import com.muhammadsayed.movies.domain.usecase.MoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideMoviesUseCases(
        movieRepository: MovieRepository,
    ): MoviesUseCases {
        return MoviesUseCases(
            getTrendingMoviesUseCase = GetTrendingMoviesUseCase(
                movieRepository = movieRepository
            )
        )
    }
}


