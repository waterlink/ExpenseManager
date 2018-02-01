package com.iwillteachyoukotlin.expensemanager.domain.expense

interface ExpenseRepository {
    fun createExpense(expense: Expense)
    fun find(id: String): Expense?
}