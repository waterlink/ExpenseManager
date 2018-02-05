package com.iwillteachyoukotlin.expensemanager.presentation.util

import org.mockito.Mockito

fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}

private fun <T> uninitialized() =
        null as T