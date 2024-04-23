package com.muhammadsayed.domain.di

import com.muhammadsayed.common.di.IoDispatcher
import com.muhammadsayed.domain.repository.MovieDetailsRepository
import com.muhammadsayed.domain.usecase.GetMovieDetailsUseCase
import com.muhammadsayed.domain.usecase.MovieDetailsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailsDomainModule {
    @Provides
    fun provideMovieDetailsUseCases(
        movieRepository: MovieDetailsRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): MovieDetailsUseCases {
        return MovieDetailsUseCases(
            getMovieDetailsUseCase = GetMovieDetailsUseCase(
                movieDetailsRepository = movieRepository,
                coroutineDispatcher = coroutineDispatcher
            )
        )
    }
}