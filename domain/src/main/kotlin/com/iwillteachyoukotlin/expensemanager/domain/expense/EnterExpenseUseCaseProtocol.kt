package com.iwillteachyoukotlin.expensemanager.domain.expense

import com.iwillteachyoukotlin.expensemanager.domain.util.Result

interface EnterExpenseUseCaseProtocol {
    fun enterExpense(expenseData: ExpenseData): Result<EnteredExpense>
}