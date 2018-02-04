package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.iwillteachyoukotlin.expensemanager.R
import com.iwillteachyoukotlin.expensemanager.domain.expense.ShowExpenseDetailsUseCase

open class ShowExpenseDetailsActivity : AppCompatActivity() {

    private lateinit var showExpenseDetailsUseCase: ShowExpenseDetailsUseCase

    fun withShowExpenseDetailsUseCase(
            showExpenseDetailsUseCase: ShowExpenseDetailsUseCase)
            : ShowExpenseDetailsActivity {
        this.showExpenseDetailsUseCase = showExpenseDetailsUseCase
        return this
    }

    private lateinit var expense_details_date_view: TextView
    private lateinit var expense_details_cost_view: TextView
    private lateinit var expense_details_needs_reimbursement_view: TextView
    private lateinit var expense_details_client_related_view: TextView
    private lateinit var expense_details_comment_view: TextView

    fun withViews(
            expense_details_date_view: TextView,
            expense_details_cost_view: TextView,
            expense_details_needs_reimbursement_view: TextView,
            expense_details_client_related_view: TextView,
            expense_details_comment_view: TextView): ShowExpenseDetailsActivity {

        this.expense_details_date_view = expense_details_date_view
        this.expense_details_cost_view = expense_details_cost_view
        this.expense_details_needs_reimbursement_view = expense_details_needs_reimbursement_view
        this.expense_details_client_related_view = expense_details_client_related_view
        this.expense_details_comment_view = expense_details_comment_view

        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_expense_details)
    }

    internal fun fetchAndShowExpenseDetails() {

    }

}
