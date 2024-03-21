package com.muhammadsayed.movies.di

import com.muhammadsayed.movies.data.remote.MoviesService
import com.muhammadsayed.movies.data.repository.MovieRepositoryImpl
import com.muhammadsayed.movies.domain.repository.MovieRepository
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
    fun provideMovieRepository(moviesService: MoviesService): MovieRepository {
        return MovieRepositoryImpl(apiService = moviesService)
    }

}