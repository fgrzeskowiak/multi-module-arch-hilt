package com.filippo.feature.account.data

import com.filippo.core.session.domain.NoActiveSessionError
import com.filippo.core.session.domain.SessionProvider
import com.filippo.domain.AccountDetails
import com.filippo.domain.AccountRepository
import com.filippo.feature.account.data.mappers.toDomainModel
import com.filippo.feature.account.data.remote.AccountApi
import com.filippo.feature.account.data.remote.models.AddToFavouritesRequest
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val sessionProvider: SessionProvider
) : AccountRepository {
    private var accountDetails: AccountDetails? = null

    override suspend fun accountDetails(): AccountDetails = accountDetails
        ?: kotlin.run {
            val sessionId = sessionProvider.getSessionId()
            if (sessionId != null) {
                accountApi.accountDetails(sessionId.id).toDomainModel().also { accountDetails = it }
            } else {
                throw NoActiveSessionError
            }
        }

    override suspend fun addToFavourites(movieId: Int) {
        val sessionId = sessionProvider.getSessionId()
        val accountId = accountDetails().id

        if (sessionId != null) {
            accountApi.modifyFavourites(
                accountId,
                sessionId.id,
                AddToFavouritesRequest(
                    AddToFavouritesRequest.MediaType.MOVIE,
                    movieId,
                    favorite = true
                )
            )
        } else {
            throw NoActiveSessionError
        }
    }

    override suspend fun removeFromFavourites(movieId: Int) {
        val sessionId = sessionProvider.getSessionId()
        val accountId = accountDetails().id

        if (sessionId != null) {
            accountApi.modifyFavourites(
                accountId,
                sessionId.id,
                AddToFavouritesRequest(
                    AddToFavouritesRequest.MediaType.MOVIE,
                    movieId,
                    favorite = false
                )
            )
        } else {
            throw NoActiveSessionError
        }
    }

    override suspend fun isLoggedIn(): Boolean = sessionProvider.isLoggedIn()

    override suspend fun getFavourites(): List<Int> {
        val sessionId = sessionProvider.getSessionId()
        return if (sessionId != null) {
            accountApi
                .favouriteMovies(accountId = accountDetails().id, sessionId = sessionId.id)
                .movies
                .map { it.id }
        } else {
            emptyList()
        }
    }
}
