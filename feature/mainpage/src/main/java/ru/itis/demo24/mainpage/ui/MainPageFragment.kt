package ru.itis.demo24.mainpage.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.itis.demo24.base.BaseFragment
import ru.itis.demo24.mainpage.R
import ru.itis.demo24.mainpage.databinding.FragmentMainpageBinding
import ru.itis.demo24.mainpage.utils.observe

@AndroidEntryPoint
class MainPageFragment : BaseFragment(R.layout.fragment_mainpage) {

    private val viewModel: MainPageViewModel by viewModels()

    private var _viewBinding: FragmentMainpageBinding? = null
    private val viewBinding: FragmentMainpageBinding
        get() = _viewBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = FragmentMainpageBinding.bind(view)
        initViews()
        observeData()
    }

    private fun initViews() {
        viewBinding.actionBtn.setOnClickListener {
            viewModel.getRandomFact()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.dataFlow.observe(lifecycleOwner = this@MainPageFragment.viewLifecycleOwner) { data ->

            }
        }
    }
}
