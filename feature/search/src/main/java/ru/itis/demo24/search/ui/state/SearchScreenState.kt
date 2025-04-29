package ru.itis.demo24.search.ui.state

import ru.itis.demo24.domain.model.SearchResultModel

sealed interface SearchScreenState {
    data object Initial : SearchScreenState
    data object Loading : SearchScreenState
    data class SearchResult(val result: List<SearchResultModel>) : SearchScreenState
    data class Error(val message: String?, val ex: Throwable) : SearchScreenState
}

data class SearchScreenStateData(
    val isLoading: Boolean = false,
    val searchResult: List<SearchResultModel> = emptyList(),
    val error: Throwable? = null,
)