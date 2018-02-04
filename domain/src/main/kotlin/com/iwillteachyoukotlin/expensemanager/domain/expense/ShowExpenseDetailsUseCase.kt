package com.iwillteachyoukotlin.expensemanager.domain.expense

import com.iwillteachyoukotlin.expensemanager.domain.util.Result
import com.iwillteachyoukotlin.expensemanager.domain.util.TaskExecutor

open class ShowExpenseDetailsUseCase(
        private val expenseRepository: ExpenseRepository,
        private val taskExecutor: TaskExecutor) {

    open fun showExpenseDetails(expenseId: String): Result<ExpenseDetails?> {
        return taskExecutor.execute {
            val expense = expenseRepository.find(expenseId)
            expense?.let { ExpenseDetails.from(it) }
        }
    }

}

