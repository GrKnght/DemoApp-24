package ru.itis.demo24.search.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.vbpd.viewBinding
import ru.itis.demo24.basefeature.BaseFragment
import ru.itis.demo24.basefeature.recycler.decorator.HorizontalDecorator
import ru.itis.demo24.domain.model.SearchResultModel
import ru.itis.demo24.search.databinding.FragmentSearchBinding
import ru.itis.demo24.search.ui.adapter.SearchRvAdapter
import ru.itis.demo24.search.ui.state.SearchScreenEvent
import ru.itis.demo24.search.ui.state.SearchScreenState
import ru.itis.demoapp24.core.utils.extensions.dpToPx
import ru.itis.demoapp24.core.utils.extensions.hideKeyboard
import ru.itis.demo24.search.R as searchR

@AndroidEntryPoint
class SearchFragment : BaseFragment(searchR.layout.fragment_search) {

    private val viewBinding by viewBinding(FragmentSearchBinding::bind)

    private val viewModel: SearchViewModel by viewModels()

    private var rvAdapter: SearchRvAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeStateChanges()

    }

    private fun initViews() {
        if (rvAdapter == null) {
            rvAdapter = SearchRvAdapter(
                requestManager = Glide.with(this),
                onItemClick = ::onListItemClick,
            )
        }
        viewBinding.searchResultRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            val padding = 16f.dpToPx(requireContext())
            addItemDecoration(HorizontalDecorator(padding = padding))
            adapter = rvAdapter
        }
        viewBinding.searchEt.doOnTextChanged { input, _, _, _ ->
            if (input?.isNotEmpty() == true) {
                viewModel.reduce(
                    event = SearchScreenEvent.OnSearchQueryChanged(query = input.toString())
                )
            }
        }
    }

    private fun observeStateChanges() {
        with(viewBinding) {
            viewModel.pageState.observe { screenState ->
                when (screenState) {
                    is SearchScreenState.Loading -> {
                        loadingContainer.isVisible = true
                        loadingContainer.requestFocus()
                    }

                    is SearchScreenState.SearchResult -> {
                        hideKeyboard()
                        loadingContainer.isVisible = false
                        loadingContainer.clearFocus()
                        rvAdapter?.updateItems(searchResult = screenState.result)
                    }

                    is SearchScreenState.Error -> {
                        hideKeyboard()
                        loadingContainer.isVisible = false
                        loadingContainer.clearFocus()
                        Toast.makeText(requireContext(), "Error occurred: ${screenState.ex}", Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }
    }

    /** Второй вариант реализации PageState */
//    private fun observePageStateData() {
//        with(viewBinding) {
//            viewModel.pageStateData.observe { data ->
//                when {
//                    data.isLoading -> {
//                        loadingContainer.isVisible = true
//                        loadingContainer.requestFocus()
//                    }
//
//                    data.error != null -> {
//                        hideKeyboard()
//                        loadingContainer.isVisible = false
//                        loadingContainer.clearFocus()
//                        Toast.makeText(requireContext(), "Error occurred: ${data.error}", Toast.LENGTH_SHORT).show()
//                    }
//
//                    data.searchResult.isNotEmpty() -> {
//                        hideKeyboard()
//                        loadingContainer.isVisible = false
//                        loadingContainer.clearFocus()
//                        rvAdapter?.updateItems(searchResult = data.searchResult)
//                    }
//
//                    else -> Unit
//                }
//            }
//        }
//    }

    private fun onListItemClick(resultModel: SearchResultModel) {
        viewModel.reduce(event = SearchScreenEvent.OnListItemClick(item = resultModel))
    }
}