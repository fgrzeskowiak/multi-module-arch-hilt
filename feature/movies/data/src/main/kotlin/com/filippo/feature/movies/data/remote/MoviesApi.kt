package com.filippo.feature.movies.data.remote

import com.filippo.feature.movies.data.remote.models.MovieDetailsResponse
import com.filippo.feature.movies.data.remote.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MoviesApi {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailsResponse
}
