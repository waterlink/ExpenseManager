package com.iwillteachyoukotlin.expensemanager.domain.expense

class InMemoryExpenseRepository : ExpenseRepository {
    private val expenses = mutableListOf<Expense>()

    override fun createExpense(expense: Expense) {
        expenses.add(expense)
    }

    fun findAllExpenses(): List<Expense> {
        return expenses
    }
}