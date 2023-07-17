package com.filippo.feature.moveis.presentation.details.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippo.core.ui.ViewState
import com.filippo.core.ui.toViewState
import com.filippo.feature.movies.domain.FavouritesRepository
import com.filippo.feature.movies.domain.MoviesRepository
import com.filippo.feature.movies.domain.models.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

@HiltViewModel
internal class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val favouritesRepository: FavouritesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val movieId = savedStateHandle.get<Int>(MOVIE_ID_KEY) ?: -1

    private val refreshFlow = MutableSharedFlow<Unit>()

    val state: Flow<ViewState<MovieDetails>> = refreshFlow
        .onStart { emit(Unit) }
        .flatMapLatest {
            flow { emit(moviesRepository.getMovieDetails(movieId)) }
                .toViewState()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ViewState.Loading)

    private val addToFavouritesFlow = MutableSharedFlow<Unit>()

    private val isOnFavourites = flow { emit(favouritesRepository.canShowFavourites()) }
        .flatMapLatest { canShowFavourites ->
            if (canShowFavourites) {
                favouritesRepository.favouritesList
                    .map { FavouritesState.Show(isOnFavourites = movieId in it) }
            } else {
                flowOf(FavouritesState.Hide)
            }
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)

    val addToFavouritesState = addToFavouritesFlow
        .flatMapLatest {
            isOnFavourites
                .filterIsInstance<FavouritesState.Show>()
                .take(1)
                .flatMapLatest {
                    flow { emit(favouritesRepository.modifyFavourites(movieId, it.isOnFavourites)) }
                        .map { true }
                        .catch { emit(false) }
                }
        }
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())


    val favouritesState: Flow<FavouritesState> = isOnFavourites

    fun addToFavourites() {
        viewModelScope.launch {
            addToFavouritesFlow.emit(Unit)
        }
    }
}

private const val MOVIE_ID_KEY = "movie_id"

internal sealed class FavouritesState {
    data class Show(val isOnFavourites: Boolean) : FavouritesState()
    object Hide : FavouritesState()
}
