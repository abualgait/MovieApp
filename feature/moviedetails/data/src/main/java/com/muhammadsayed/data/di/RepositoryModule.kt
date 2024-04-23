package com.muhammadsayed.data.di

import com.muhammadsayed.data.remote.MoviesDetailsService
import com.muhammadsayed.data.repository.MovieDetailsRepositoryImpl
import com.muhammadsayed.domain.repository.MovieDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(
        moviesDetailsService: MoviesDetailsService,
    ): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(
            detailApiService = moviesDetailsService
        )
    }

}