package com.iwillteachyoukotlin.expensemanager.domain.expense

import java.time.LocalDate

data class Expense(val id: String,
                   val comment: String,
                   val date: LocalDate,
                   val cost: Money,
                   val needsReimbursement: Boolean,
                   val clientRelated: Boolean)