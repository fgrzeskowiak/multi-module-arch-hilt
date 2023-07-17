package com.filippo.feature.account.data.remote

import com.filippo.feature.account.data.remote.models.AccountResponse
import com.filippo.feature.account.data.remote.models.AddToFavouritesRequest
import com.filippo.feature.account.data.remote.models.FavouritesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface AccountApi {
    @GET("3/account")
    suspend fun accountDetails(@Query("session_id") sessionId: String): AccountResponse

    @GET("3/account/{account_id}/favorite/movies")
    suspend fun favouriteMovies(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): FavouritesResponse

    @POST("3/account/{account_id}/favorite")
    suspend fun modifyFavourites(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body addToFavouritesRequest: AddToFavouritesRequest
    )
}
