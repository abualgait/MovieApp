package com.muhammadsayed.data.remote

import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.data.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDetailsService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
    ): MovieDetail
}