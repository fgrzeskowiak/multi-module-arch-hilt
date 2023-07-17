package com.filippo.feature.movies.domain.models

import android.graphics.drawable.Drawable

data class MovieDetails(
    val budget: Double,
    val genres: List<Genre>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val poster: Drawable?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}
