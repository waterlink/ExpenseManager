package com.iwillteachyoukotlin.expensemanager.domain.expense

import com.iwillteachyoukotlin.expensemanager.domain.expense.Expense
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseRepository

class InMemoryExpenseRepository : ExpenseRepository {

    companion object {
        val instance = InMemoryExpenseRepository()
    }

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