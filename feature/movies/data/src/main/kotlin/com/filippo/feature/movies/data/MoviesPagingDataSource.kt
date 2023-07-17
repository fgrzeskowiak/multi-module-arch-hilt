package com.filippo.feature.movies.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.filippo.core.images.domain.ImageSize
import com.filippo.core.images.domain.ImagesRepository
import com.filippo.feature.movies.data.mappers.toDomainModel
import com.filippo.feature.movies.data.remote.MoviesApi
import com.filippo.feature.movies.data.remote.models.MoviesResponse
import com.filippo.feature.movies.domain.models.Movie
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

internal class MoviesPagingDataSource @Inject constructor(
    private val moviesApi: MoviesApi,
    private val imagesRepository: ImagesRepository,
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> =
        moviesApi
            .runCatching { getPopularMovies(page = params.key ?: INITIAL_PAGE) }
            .fold(
                onSuccess = { response ->
                    val images = imageRequestMap(response.movies)
                    LoadResult.Page(
                        response.movies.map { it.toDomainModel(images[it.id]?.await()?.drawable) },
                        prevKey = if (response.page == INITIAL_PAGE) null else response.page - 1,
                        nextKey = if (response.page == response.totalPages) null else response.page + 1
                    )
                },
                onFailure = { LoadResult.Error(it) }
            )

    private suspend fun imageRequestMap(movies: List<MoviesResponse.Movie>) =
        supervisorScope {
            movies.associate { movie ->
                movie.id to async {
                    imagesRepository.downloadImage(movie.posterPath, ImageSize.SMALL)
                }
            }
        }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}
