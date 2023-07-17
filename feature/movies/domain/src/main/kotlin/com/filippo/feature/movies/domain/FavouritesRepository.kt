package com.filippo.feature.movies.domain

import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun canShowFavourites(): Boolean
    suspend fun modifyFavourites(movieId: Int, isOnFavourites: Boolean)
    suspend fun triggerInitialFavourites()
    val favouritesList: Flow<List<Int>>
}
