package ru.itis.demoapp24.core.utils.extensions

fun String?.orEmpty(): String {
    return if (!this.isNullOrEmpty()) this else ""
}