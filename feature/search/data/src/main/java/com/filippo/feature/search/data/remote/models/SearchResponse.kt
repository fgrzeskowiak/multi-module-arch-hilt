package com.filippo.feature.search.data.remote.models

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class SearchResponse(
    val page: Int,
    val results: List<Result>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    @Serializable
    data class Result(
        val adult: Boolean,
        val id: Int,
        @SerialName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerialName("poster_path")
        val posterPath: String?,
        @SerialName("release_date")
        val releaseDate: String?,
        val title: String,
        @SerialName("vote_average")
        val voteAverage: Double,
        @SerialName("vote_count")
        val voteCount: Int
    )
}
