package ru.itis.demo24.search.ui.state

import ru.itis.demo24.domain.model.SearchResultModel

sealed interface SearchScreenEvent {
    data class OnSearchQueryChanged(val query: String) : SearchScreenEvent
    data class OnListItemClick(val item: SearchResultModel) : SearchScreenEvent
}