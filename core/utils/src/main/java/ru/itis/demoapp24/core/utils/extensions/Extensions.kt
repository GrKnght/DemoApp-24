package ru.itis.demoapp24.core.utils.extensions

import android.content.Context
import android.util.DisplayMetrics

fun Context.pxToDp(input: Int): Int =
    (input / (this.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun Context.dpToPx(input: Int): Int = (input * this.resources.displayMetrics.density).toInt()

fun Float.pxToDp(ctx: Context): Float =
    (this / (ctx.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun Float.dpToPx(ctx: Context): Float = this * ctx.resources.displayMetrics.density

fun Int.pxToDp(ctx: Context): Int =
    (this / (ctx.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))

fun Int.dpToPx(ctx: Context): Int = (this * ctx.resources.displayMetrics.density).toInt()
