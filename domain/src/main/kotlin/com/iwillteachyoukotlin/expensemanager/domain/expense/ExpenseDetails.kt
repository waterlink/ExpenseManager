package com.iwillteachyoukotlin.expensemanager.domain.expense

import java.time.LocalDate

data class ExpenseDetails(val comment: String,
                          val date: LocalDate,
                          val cost: Money,
                          val needsReimbursement: Boolean,
                          val clientRelated: Boolean) {

    companion object {
        fun from(expense: Expense) =
                ExpenseDetails(
                        comment = expense.comment,
                        date = expense.date,
                        cost = expense.cost,
                        needsReimbursement = expense.needsReimbursement,
                        clientRelated = expense.clientRelated
                )
    }

}