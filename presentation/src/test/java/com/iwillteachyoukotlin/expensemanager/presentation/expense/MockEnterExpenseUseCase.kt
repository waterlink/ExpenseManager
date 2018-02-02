package com.iwillteachyoukotlin.expensemanager.presentation.expense

import com.iwillteachyoukotlin.expensemanager.domain.expense.EnterExpenseUseCaseProtocol
import com.iwillteachyoukotlin.expensemanager.domain.expense.EnteredExpense
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseData
import com.iwillteachyoukotlin.expensemanager.domain.util.DirectTaskExecutor
import com.iwillteachyoukotlin.expensemanager.domain.util.Result

class MockEnterExpenseUseCase : EnterExpenseUseCaseProtocol {
    var savedExpense: ExpenseData? = null

    override fun enterExpense(expenseData: ExpenseData): Result<EnteredExpense> {
        return DirectTaskExecutor().execute {
            savedExpense = expenseData
            EnteredExpense(id = "generated-expense-id")
        }
    }
}