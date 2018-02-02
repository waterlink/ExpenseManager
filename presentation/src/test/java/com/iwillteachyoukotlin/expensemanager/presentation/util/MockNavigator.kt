package com.iwillteachyoukotlin.expensemanager.presentation.util

import android.content.Context

class MockNavigator : Navigator {

    var shownExpenseDetails = false

    override fun showExpenseDetails(context: Context) {
        shownExpenseDetails = true
    }

}