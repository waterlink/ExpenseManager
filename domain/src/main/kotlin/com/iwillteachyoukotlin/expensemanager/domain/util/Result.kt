package com.iwillteachyoukotlin.expensemanager.domain.util

class Result<T> {

    private var resolvedValue: T? = null

    private var onSuccessBlock: ((T) -> Unit)? = null

    fun onSuccess(block: (T) -> Unit) {
        onSuccessBlock = block
        resolvedValue?.let(block)
    }

    private var isResolved = false

    fun isResolved(): Boolean {
        return isResolved
    }

    fun resolve(value: T) {
        isResolved = true
        resolvedValue = value
        onSuccessBlock?.invoke(value)
    }
}