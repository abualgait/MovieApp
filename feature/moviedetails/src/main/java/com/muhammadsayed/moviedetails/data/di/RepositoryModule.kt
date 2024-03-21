package com.muhammadsayed.moviedetails.data.di

import com.muhammadsayed.common.di.DefaultDispatcher
import com.muhammadsayed.moviedetails.data.remote.MoviesDetailsService
import com.muhammadsayed.moviedetails.data.repository.MovieDetailsRepositoryImpl
import com.muhammadsayed.moviedetails.domain.repository.MovieDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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