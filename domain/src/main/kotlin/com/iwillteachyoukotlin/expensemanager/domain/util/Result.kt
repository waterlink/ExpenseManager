package com.iwillteachyoukotlin.expensemanager.domain.util

class Result<T> {
    fun onSuccess(block: (T) -> Unit) {

    }

    fun isResolved(): Boolean {
        return true
    }
}