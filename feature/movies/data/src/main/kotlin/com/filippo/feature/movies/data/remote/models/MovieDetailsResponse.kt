package com.filippo.feature.movies.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDetailsResponse(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val budget: Double,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    @Serializable
    data class Genre(
        val id: Int,
        val name: String
    )

    @Serializable
    data class ProductionCompany(
        val id: Int,
        @SerialName("logo_path")
        val logoPath: String?,
        val name: String,
        @SerialName("origin_country")
        val originCountry: String
    )

    @Serializable
    data class ProductionCountry(val name: String)

    @Serializable
    data class SpokenLanguage(val name: String)
}
