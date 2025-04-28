package ru.itis.demo24.mainpage.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.itis.demo24.domain.usecase.GetRandomFactUseCase
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val getRandomFactUseCase: GetRandomFactUseCase,
) : ViewModel() {

    private val _sampleStateFlow = MutableStateFlow(0)

    private val _dataFlow = MutableSharedFlow<Int>()
    val dataFlow: SharedFlow<Int> = _dataFlow.asSharedFlow()

    fun getRandomFact() {

    }
}