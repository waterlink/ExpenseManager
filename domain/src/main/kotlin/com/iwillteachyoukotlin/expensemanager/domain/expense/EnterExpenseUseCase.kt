package com.iwillteachyoukotlin.expensemanager.domain.expense

import com.iwillteachyoukotlin.expensemanager.domain.util.IdSource
import com.iwillteachyoukotlin.expensemanager.domain.util.Result
import com.iwillteachyoukotlin.expensemanager.domain.util.TaskExecutor

class EnterExpenseUseCase(
        private val expenseRepository: ExpenseRepository,
        private val taskExecutor: TaskExecutor,
        private val idSource: IdSource) {

    fun enterExpense(expenseData: ExpenseData): Result<EnteredExpense> {
        return taskExecutor.execute {

            val cost = Money(
                    cents = expenseData.cents,
                    currency = Currency.EUR
            )

            val id = idSource.generateId()
            expenseRepository.createExpense(Expense(
                    id = id,
                    comment = expenseData.comment,
                    date = expenseData.date,
                    cost = cost,
                    needsReimbursement = expenseData.needsReimbursement,
                    clientRelated = expenseData.clientRelated
            ))

            EnteredExpense(id = id)
        }
    }

}