package com.filippo.feature.search.data.mappers

import android.graphics.drawable.Drawable
import com.filippo.feature.search.data.remote.models.SearchResponse
import com.filippo.feature.search.domain.SearchResult

internal fun SearchResponse.Result.toDomainModel(posterDrawable: Drawable?) = SearchResult(
    id = id,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    poster = posterDrawable,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage
)
