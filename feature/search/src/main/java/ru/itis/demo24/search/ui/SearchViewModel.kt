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
import ru.itis.demo24.domain.model.SearchResultModel
import ru.itis.demo24.domain.usecase.SearchDataUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchDataUseCase: SearchDataUseCase,
) : ViewModel() {

    private val searchFlow = MutableStateFlow(value = "")

    private val _loadingState = MutableStateFlow(value = false)
    val loadingState = _loadingState.asStateFlow()

    private val _searchResultState = MutableStateFlow<List<SearchResultModel>>(value = emptyList())
    val searchResultState = _searchResultState.asStateFlow()

    private val _inputFocusState = MutableStateFlow(value = false)
    val inputFocusState = _inputFocusState.asStateFlow()

    init {
        observeTextChanges()
    }

    fun onSearchQueryChanged(input: String) = searchFlow.tryEmit(input)

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
            _loadingState.value = true
            _inputFocusState.value = true
            delay(2000L)
            runCatching {
                searchDataUseCase.invoke(query = query)
            }.onSuccess { result ->
                _loadingState.value = false
                _inputFocusState.value = false
                result.forEach {
                    println("TEST TAG - Received Song: ${it.title}")
                }
            }.onFailure {
                _loadingState.value = false
                _inputFocusState.value = false
                println("TEST TAG - Error Occurred: $it")
            }
        }
    }
}