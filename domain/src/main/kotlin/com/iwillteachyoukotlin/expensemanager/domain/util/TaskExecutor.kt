package com.iwillteachyoukotlin.expensemanager.domain.util

interface TaskExecutor {
    fun <T> execute(block: () -> T): Result<T>
}