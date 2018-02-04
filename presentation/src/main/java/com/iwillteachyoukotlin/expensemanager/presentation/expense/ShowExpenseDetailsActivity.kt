package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.iwillteachyoukotlin.expensemanager.R
import com.iwillteachyoukotlin.expensemanager.data.expense.InMemoryExpenseRepository
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseDetails
import com.iwillteachyoukotlin.expensemanager.domain.expense.ShowExpenseDetailsUseCase
import com.iwillteachyoukotlin.expensemanager.domain.util.DirectTaskExecutor
import kotlinx.android.synthetic.main.activity_show_expense_details.*
import java.text.DateFormat
import java.text.NumberFormat

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

        val showExpenseDetailsUseCase = ShowExpenseDetailsUseCase(
                InMemoryExpenseRepository.instance,
                DirectTaskExecutor()
        )
        withShowExpenseDetailsUseCase(showExpenseDetailsUseCase)

        withViews(
                expense_details_date,
                expense_details_cost,
                expense_details_needs_reimbursement,
                expense_details_client_related,
                expense_details_comment
        )
    }

    override fun onStart() {
        super.onStart()
        fetchAndShowExpenseDetails()
    }

    internal fun fetchAndShowExpenseDetails() {
        val expenseId = intent.getStringExtra("expenseId")
        showExpenseDetailsUseCase.showExpenseDetails(expenseId)
                .onSuccess(this::renderExpenseDetails)
    }

    private fun renderExpenseDetails(expenseDetails: ExpenseDetails?) {
        expenseDetails ?: return

        val dateFormatter = DateFormat.getDateInstance()
        val date = dateFormatter.format(expenseDetails.date)
        expense_details_date_view.text = date

        val decimalFormatter = NumberFormat.getInstance()
        val amount = decimalFormatter.format(expenseDetails.cost.cents / 100.0)
        val currency = expenseDetails.cost.currency.name
        expense_details_cost_view.text = "$amount $currency"

        val needsReimbursementMessage =
                if (expenseDetails.needsReimbursement)
                    R.string.expense_needs_reimbursement
                else
                    R.string.expense_needs_no_reimbursement
        expense_details_needs_reimbursement_view
                .setText(needsReimbursementMessage)

        val clientRelatedMessage =
                if (expenseDetails.clientRelated)
                    R.string.expense_client_related
                else
                    R.string.expense_not_client_related
        expense_details_client_related_view
                .setText(clientRelatedMessage)

        expense_details_comment_view.text = expenseDetails.comment
    }

}
