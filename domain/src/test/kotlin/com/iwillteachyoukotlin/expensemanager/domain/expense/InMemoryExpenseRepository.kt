package com.iwillteachyoukotlin.expensemanager.domain.expense

class InMemoryExpenseRepository : ExpenseRepository {
    private val expenses = mutableListOf<Expense>()

    override fun createExpense(expense: Expense) {
        expenses.add(expense)
    }

    override fun find(id: String): Expense? {
        return expenses.find { it.id == id }
    }

    fun findAllExpenses(): List<Expense> {
        return expenses
    }
}