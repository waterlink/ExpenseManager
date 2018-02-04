package com.iwillteachyoukotlin.expensemanager.domain.expense

import java.util.*

data class ExpenseData(val comment: String,
                       val date: Date,
                       val cents: Int,
                       val currency: String,
                       val needsReimbursement: Boolean,
                       val clientRelated: Boolean)