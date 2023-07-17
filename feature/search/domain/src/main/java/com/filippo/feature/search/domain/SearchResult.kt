package com.filippo.feature.search.domain

import android.graphics.drawable.Drawable

data class SearchResult(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val poster: Drawable?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double
)
