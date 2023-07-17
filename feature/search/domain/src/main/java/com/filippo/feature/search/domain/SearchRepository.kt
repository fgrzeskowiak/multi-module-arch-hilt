package com.filippo.feature.search.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchResults(query: String): Flow<PagingData<SearchResult>>
}
