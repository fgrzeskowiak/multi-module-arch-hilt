package com.filippo.feature.movies.data.mappers

import android.graphics.drawable.Drawable
import com.filippo.feature.movies.data.remote.models.MovieDetailsResponse
import com.filippo.feature.movies.data.remote.models.MoviesResponse
import com.filippo.feature.movies.domain.models.Movie
import com.filippo.feature.movies.domain.models.MovieDetails

internal fun MoviesResponse.Movie.toDomainModel(posterDrawable: Drawable?) = Movie(
    id = id,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    poster = posterDrawable,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage
)

internal fun MovieDetailsResponse.toDomainModel(posterDrawable: Drawable?): MovieDetails = MovieDetails(
    budget = budget,
    genres = genres.map { MovieDetails.Genre(it.id, it.name) },
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    poster = posterDrawable,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)
