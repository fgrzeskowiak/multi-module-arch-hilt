package com.filippo.feature.movies.domain.models

import android.graphics.drawable.Drawable

data class Movie(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val poster: Drawable?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)
