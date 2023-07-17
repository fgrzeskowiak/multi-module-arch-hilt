package com.filippo.feature.search.presentation

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.filippo.feature.search.domain.SearchRepository
import com.filippo.feature.search.domain.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    searchRepository: SearchRepository
) : ViewModel() {
    private val queryFlow = MutableSharedFlow<String>()

    val searchItems = queryFlow
        .debounce(DEBOUNCE_TIME)
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                emptyFlow()
            } else {
                searchRepository.getSearchResults(query)
                    .map { pagingData -> pagingData.map { it.toListItem() } }
            }
        }
        .cachedIn(viewModelScope)

    private fun SearchResult.toListItem() =
        SearchListItem(id, poster, voteAverage.toString(), releaseDate, title)

    fun search(query: String?) {
        viewModelScope.launch {
            queryFlow.emit(query.orEmpty())
        }
    }
}

private const val DEBOUNCE_TIME = 300L

data class SearchListItem(
    val id: Int,
    val poster: Drawable?,
    val rating: String,
    val releaseDate: String?,
    val title: String,
)

