package com.filippo.feature.search.data.remote

import com.filippo.feature.search.data.remote.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchApi {
    @GET("3/search/movie")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int
    ): SearchResponse
}
