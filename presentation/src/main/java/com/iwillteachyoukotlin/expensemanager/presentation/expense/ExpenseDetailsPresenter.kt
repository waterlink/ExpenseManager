package com.iwillteachyoukotlin.expensemanager.presentation.expense

import com.iwillteachyoukotlin.expensemanager.R
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseDetails
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

class ExpenseDetailsPresenter(expenseDetails: ExpenseDetails) {
    private val dateFormatter = DateFormat.getDateInstance()

    val date = dateFormatter.format(expenseDetails.date)

    private val currencyFormatter = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance(expenseDetails.cost.currency.name)
    }

    val cost = currencyFormatter.format(expenseDetails.cost.cents / 100.0)

    val reimbursementLabel =
            if (expenseDetails.needsReimbursement)
                R.string.expense_needs_reimbursement
            else
                R.string.expense_needs_no_reimbursement

    val clientRelatedLabel =
            if (expenseDetails.clientRelated)
                R.string.expense_client_related
            else
                R.string.expense_not_client_related

    val comment = expenseDetails.comment
}