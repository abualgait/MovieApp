package com.muhammadsayed.movies.data.remote

import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.movies.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    suspend fun getListOfTrendingMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int = 1
    ): Movie
}