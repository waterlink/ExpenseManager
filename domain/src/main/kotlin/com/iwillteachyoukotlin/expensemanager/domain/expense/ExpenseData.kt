package com.iwillteachyoukotlin.expensemanager.domain.expense

import java.time.LocalDate

data class ExpenseData(val comment: String,
                       val date: LocalDate,
                       val cents: Int,
                       val currency: String,
                       val needsReimbursement: Boolean,
                       val clientRelated: Boolean)