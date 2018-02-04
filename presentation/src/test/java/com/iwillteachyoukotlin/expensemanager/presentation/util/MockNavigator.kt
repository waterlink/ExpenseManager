package com.iwillteachyoukotlin.expensemanager.presentation.util

import android.content.Context

class MockNavigator : Navigator {

    var shownExpenseDetails = false
    var shownExpenseDetailsWithExpenseId = ""

    override fun showExpenseDetails(context: Context, expenseId: String) {
        shownExpenseDetails = true
        shownExpenseDetailsWithExpenseId = expenseId
    }

}