package com.filippo.domain

interface AccountRepository {
    suspend fun accountDetails(): AccountDetails
    suspend fun addToFavourites(movieId: Int)
    suspend fun removeFromFavourites(movieId: Int)
    suspend fun getFavourites(): List<Int>
    suspend fun isLoggedIn(): Boolean
}
