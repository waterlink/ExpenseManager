package com.iwillteachyoukotlin.expensemanager.domain.expense

import com.iwillteachyoukotlin.expensemanager.domain.util.Result

class EnterExpenseUseCase {
    fun enterExpense(expenseData: ExpenseData): Result<EnteredExpense> {
        return Result()
    }
}