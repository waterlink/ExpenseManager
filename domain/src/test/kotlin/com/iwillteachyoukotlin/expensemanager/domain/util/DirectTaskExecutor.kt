package com.iwillteachyoukotlin.expensemanager.domain.util

class DirectTaskExecutor : TaskExecutor {
    override fun <T> execute(block: () -> T): Result<T> {
        val value = block.invoke()
        val result = Result<T>()
        result.resolve(value)
        return result
    }
}