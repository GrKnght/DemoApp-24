package ru.itis.demo24.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import ru.itis.demo24.domain.usecase.SearchDataUseCase
import ru.itis.demo24.navigation.NavMain
import ru.itis.demo24.search.ui.state.SearchScreenEvent
import ru.itis.demo24.search.ui.state.SearchScreenState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchDataUseCase: SearchDataUseCase,
    private val navMain: NavMain,
) : ViewModel() {

    private val _pageState = MutableStateFlow<SearchScreenState>(value = SearchScreenState.Initial)
    val pageState = _pageState.asStateFlow()

    /** Второй вариант реализации PageState */
    // private val _pageStateData = MutableStateFlow(value = SearchScreenStateData())
    // val pageStateData = _pageStateData.asStateFlow()

    private val searchFlow = MutableStateFlow(value = "")

    init {
        observeTextChanges()
    }

    fun reduce(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchQueryChanged -> {
                searchFlow.tryEmit(event.query)
            }

            is SearchScreenEvent.OnListItemClick -> {
                navMain.goToDetailsPage()
            }

            else -> throw IllegalStateException("Incorrect event: $event")
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeTextChanges() {
        viewModelScope.launch {
            searchFlow
                .debounce(1000L)
                .collect { query ->
                    if (query.isEmpty()) return@collect
                    searchForQuery(query = query)
                }
        }
    }

    private fun searchForQuery(query: String) {
        viewModelScope.launch {
            _pageState.value = SearchScreenState.Loading
            // Другой вариант реализации PageState
            // _pageStateData.value = _pageStateData.value.copy(isLoading = true)
            delay(2000L)
            runCatching {
                searchDataUseCase.invoke(query = query)
            }.onSuccess { result ->
                _pageState.value = SearchScreenState.SearchResult(result = result)
            }.onFailure {
                _pageState.value = SearchScreenState.Error(
                    message = it.message,
                    ex = it
                )
            }
        }
    }
}