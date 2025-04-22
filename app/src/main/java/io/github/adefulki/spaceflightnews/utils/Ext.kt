package io.github.adefulki.spaceflightnews.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

fun Int?.orZero(): Int = this ?: 0
fun Long?.orZero(): Long = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Float?.orZero(): Float = this ?: 0.0f
fun Boolean?.orFalse(): Boolean = this ?: false
fun Boolean?.orTrue(): Boolean = this ?: true
fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}