package com.iwillteachyoukotlin.expensemanager.domain.expense

import java.util.*

data class Expense(val id: String,
                   val comment: String,
                   val date: Date,
                   val cost: Money,
                   val needsReimbursement: Boolean,
                   val clientRelated: Boolean)