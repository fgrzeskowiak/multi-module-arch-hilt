package com.filippo.feature.movies.data.remote.models

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
internal data class MoviesResponse(
    val page: Int,
    @SerialName("results") val movies: List<Movie>,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("total_pages") val totalPages: Int
) {
    @Serializable
    data class Movie(
        val adult: Boolean,
        @SerialName("backdrop_path")
        val backdropPath: String,
        @SerialName("genre_ids")
        val genreIds: List<Int>,
        val id: Int,
        @SerialName("original_language")
        val originalLanguage: String,
        @SerialName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerialName("poster_path")
        val posterPath: String,
        @SerialName("release_date")
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        @SerialName("vote_average")
        val voteAverage: Double,
        @SerialName("vote_count")
        val voteCount: Int
    )
}
