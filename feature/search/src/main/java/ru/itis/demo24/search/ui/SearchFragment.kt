package ru.itis.demo24.search.ui

import android.os.Bundle
import android.view.View
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
        observeData()
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
                viewModel.onSearchQueryChanged(input = input.toString())
            }
        }
    }

    private fun observeData() {
        with(viewBinding) {
            viewModel.searchResultState.observe { searchResult ->
                rvAdapter?.updateItems(searchResult)
            }

            viewModel.loadingState.observe { isLoadingVisible ->
                loadingContainer.isVisible = isLoadingVisible
            }

            viewModel.inputFocusState.observe { isClearFocus ->
                if (isClearFocus) {
                    loadingContainer.requestFocus()
                } else {
                    loadingContainer.clearFocus()
                    hideKeyboard()
                }
            }
        }
    }

    private fun onListItemClick(resultModel: SearchResultModel) {
        viewModel.goToSongDetails()
    }
}