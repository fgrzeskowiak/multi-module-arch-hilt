package com.filippo.feature.moveis.presentation.details.popular

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.filippo.feature.movies.domain.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@HiltViewModel
internal class PopularMoviesViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {
    val popularMovies: Flow<PagingData<MovieListItem>> = moviesRepository.popularMovies
        .map { data ->
            data.map {
                MovieListItem(it.id, it.poster, it.voteAverage.toString(), it.releaseDate, it.title)
            }
        }
        .cachedIn(viewModelScope)
}

data class MovieListItem(
    val id: Int,
    val poster: Drawable?,
    val rating: String,
    val releaseDate: String,
    val title: String,
)
