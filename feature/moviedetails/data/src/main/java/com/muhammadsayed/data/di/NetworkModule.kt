package com.muhammadsayed.data.di

import com.muhammadsayed.data.remote.MoviesDetailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMoviesDetailsService(retrofit: Retrofit): MoviesDetailsService =
        retrofit.create(MoviesDetailsService::class.java)

}