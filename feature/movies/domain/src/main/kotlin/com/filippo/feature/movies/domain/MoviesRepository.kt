package com.filippo.feature.movies.domain

import androidx.paging.PagingData
import com.filippo.feature.movies.domain.models.Movie
import com.filippo.feature.movies.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    val popularMovies: Flow<PagingData<Movie>>
    suspend fun getMovieDetails(id: Int): MovieDetails
}
