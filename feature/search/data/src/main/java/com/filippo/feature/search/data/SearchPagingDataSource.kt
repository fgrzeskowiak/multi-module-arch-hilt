package com.filippo.feature.search.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.filippo.core.images.domain.ImageSize
import com.filippo.core.images.domain.ImagesRepository
import com.filippo.feature.search.data.mappers.toDomainModel
import com.filippo.feature.search.data.remote.SearchApi
import com.filippo.feature.search.data.remote.models.SearchResponse
import com.filippo.feature.search.domain.SearchResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

internal class SearchPagingDataSource @AssistedInject constructor(
    @Assisted private val query: String,
    private val imagesRepository: ImagesRepository,
    private val searchApi: SearchApi,
) : PagingSource<Int, SearchResult>() {
    @AssistedFactory
    interface Factory {
        fun create(query: String): SearchPagingDataSource
    }

    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> =
        searchApi
            .runCatching { search(query, page = params.key ?: INITIAL_PAGE) }
            .fold(
                onSuccess = { response ->
                    val images = imageRequestMap(response.results)
                    LoadResult.Page(
                        response.results.map { it.toDomainModel(images[it.id]?.await()?.drawable) },
                        prevKey = if (response.page == INITIAL_PAGE) null else response.page - 1,
                        nextKey = if (response.page == response.totalPages) null else response.page + 1
                    )
                },
                onFailure = { LoadResult.Error(it) }
            )

    private suspend fun imageRequestMap(results: List<SearchResponse.Result>) =
        supervisorScope {
            results.associate { result ->
                result.id to async {
                    imagesRepository.downloadImage(result.posterPath, ImageSize.SMALL)
                }
            }
        }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}
