package com.filippo.feature.movies.data

import androidx.paging.Pager
import androidx.paging.PagingData
import com.filippo.core.images.domain.ImagesRepository
import com.filippo.feature.movies.data.mappers.toDomainModel
import com.filippo.feature.movies.data.remote.MoviesApi
import com.filippo.feature.movies.domain.MoviesRepository
import com.filippo.feature.movies.domain.models.Movie
import com.filippo.feature.movies.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val imagesRepository: ImagesRepository,
    moviesPager: Pager<Int, Movie>
) : MoviesRepository {

    override val popularMovies: Flow<PagingData<Movie>> = moviesPager.flow

    override suspend fun getMovieDetails(id: Int): MovieDetails {
        val movieResponse = moviesApi.getMovieDetails(id)
        val poster = imagesRepository.downloadImage(movieResponse.posterPath)

        return movieResponse.toDomainModel(poster.drawable)
    }
}
