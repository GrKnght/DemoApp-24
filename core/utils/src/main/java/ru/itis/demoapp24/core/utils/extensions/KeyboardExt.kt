package ru.itis.demoapp24.core.utils.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    this.toggleKeyboard(isShow = false)
}

private fun Fragment.toggleKeyboard(isShow: Boolean) {
    val activity = this.activity
    activity ?: return
    if (activity is AppCompatActivity) {
        activity.toggleKeyboard(isShow)
    }
}

private fun AppCompatActivity.toggleKeyboard(isShow: Boolean) {
    val currentFocusedView = this.currentFocus
    currentFocusedView ?: return
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    when (isShow) {
        true -> {
            inputMethodManager.showSoftInput(currentFocusedView, InputMethodManager.SHOW_IMPLICIT)
        }

        false -> {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            currentFocusedView.clearFocus()
        }
    }
}