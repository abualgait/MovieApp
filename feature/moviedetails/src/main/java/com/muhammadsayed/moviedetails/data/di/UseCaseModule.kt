package com.muhammadsayed.moviedetails.data.di

import com.muhammadsayed.common.di.DefaultDispatcher
import com.muhammadsayed.moviedetails.domain.repository.MovieDetailsRepository
import com.muhammadsayed.moviedetails.domain.usecase.GetMovieDetailsUseCase
import com.muhammadsayed.moviedetails.domain.usecase.MovieDetailsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideMovieDetailsUseCases(
        movieRepository: MovieDetailsRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): MovieDetailsUseCases {
        return MovieDetailsUseCases(
            getMovieDetailsUseCase = GetMovieDetailsUseCase(
                movieDetailsRepository = movieRepository, defaultDispatcher = defaultDispatcher
            )
        )
    }
}


