package com.filippo.feature.movies.data

import com.filippo.core.session.domain.SessionProvider
import com.filippo.domain.AccountRepository
import com.filippo.feature.movies.domain.FavouritesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onStart

internal class FavouritesRepositoryImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val sessionProvider: SessionProvider
) : FavouritesRepository {
    private val favouritesFlow = MutableSharedFlow<List<Int>>(replay = 1)

    override suspend fun canShowFavourites(): Boolean = sessionProvider.isLoggedIn()

    override suspend fun modifyFavourites(movieId: Int, isOnFavourites: Boolean) {
        if (isOnFavourites) {
            accountRepository.removeFromFavourites(movieId)
        } else {
            accountRepository.addToFavourites(movieId)
        }
        favouritesFlow.emit(accountRepository.getFavourites())
    }

    override suspend fun triggerInitialFavourites() {
        favouritesFlow.emit(accountRepository.getFavourites())
    }

    override val favouritesList: Flow<List<Int>> = favouritesFlow
        .onStart { emit(accountRepository.getFavourites()) }
}
