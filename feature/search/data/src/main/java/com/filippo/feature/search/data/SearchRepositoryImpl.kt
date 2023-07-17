package com.filippo.feature.search.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.filippo.feature.search.domain.SearchRepository
import com.filippo.feature.search.domain.SearchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val dataSourceFactory: SearchPagingDataSource.Factory
) : SearchRepository {
    override fun getSearchResults(query: String): Flow<PagingData<SearchResult>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dataSourceFactory.create(query) }
        ).flow

    private companion object {
        const val PAGE_SIZE = 20
    }
}
