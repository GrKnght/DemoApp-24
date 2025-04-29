package ru.itis.demo24.basefeature

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import ru.itis.demoapp24.core.utils.extensions.observe

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected inline fun <T> Flow<T>.observe(
        crossinline block: (T) -> Unit
    ): Job {
        return observe(
            lifecycleOwner = this@BaseFragment.viewLifecycleOwner,
            block = block,
        )
    }
}