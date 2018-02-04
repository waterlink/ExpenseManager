package com.iwillteachyoukotlin.expensemanager.presentation.util

import android.content.Context
import android.content.Intent
import com.iwillteachyoukotlin.expensemanager.presentation.expense.ShowExpenseDetailsActivity

object RealNavigator : Navigator {

    override fun showExpenseDetails(context: Context, expenseId: String) {
        val intent = Intent(context, ShowExpenseDetailsActivity::class.java)
        intent.putExtra("expenseId", expenseId)
        context.startActivity(intent)
    }

}