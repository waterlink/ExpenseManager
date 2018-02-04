package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import com.iwillteachyoukotlin.expensemanager.R
import com.iwillteachyoukotlin.expensemanager.domain.expense.EnterExpenseUseCase
import com.iwillteachyoukotlin.expensemanager.domain.expense.EnterExpenseUseCaseProtocol
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseData
import com.iwillteachyoukotlin.expensemanager.domain.expense.InMemoryExpenseRepository
import com.iwillteachyoukotlin.expensemanager.domain.util.DirectTaskExecutor
import com.iwillteachyoukotlin.expensemanager.domain.util.UUIDSource
import com.iwillteachyoukotlin.expensemanager.presentation.util.Navigator
import com.iwillteachyoukotlin.expensemanager.presentation.util.RealNavigator
import kotlinx.android.synthetic.main.activity_enter_expense.*
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*
import java.util.Calendar.*
import kotlin.math.roundToInt

open class EnterExpenseActivity : AppCompatActivity() {

    private lateinit var navigator: Navigator

    internal fun withNavigator(navigator: Navigator): EnterExpenseActivity {
        this.navigator = navigator
        return this
    }

    private lateinit var enterExpenseUseCase: EnterExpenseUseCaseProtocol

    internal fun withEnterExpenseUseCase(
            enterExpenseUseCase: EnterExpenseUseCaseProtocol)
            : EnterExpenseActivity {
        this.enterExpenseUseCase = enterExpenseUseCase
        return this
    }

    private lateinit var expense_comment_input_view: EditText
    private lateinit var expense_date_input_view: EditText
    private lateinit var expense_amount_input_view: EditText
    private lateinit var expense_currency_spinner_view: Spinner
    private lateinit var expense_reimbursement_switch_view: Switch
    private lateinit var expense_client_related_switch_view: Switch

    internal fun withViews(
            expense_comment_input: EditText,
            expense_date_input: EditText,
            expense_amount_input: EditText,
            expense_currency_spinner: Spinner,
            expense_reimbursement_switch: Switch,
            expense_client_related_switch: Switch)
            : EnterExpenseActivity {
        expense_comment_input_view = expense_comment_input
        expense_date_input_view = expense_date_input
        expense_amount_input_view = expense_amount_input
        expense_currency_spinner_view = expense_currency_spinner
        expense_reimbursement_switch_view = expense_reimbursement_switch
        expense_client_related_switch_view = expense_client_related_switch
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_expense)

        withNavigator(RealNavigator)

        withViews(
                expense_comment_input,
                expense_date_input,
                expense_amount_input,
                expense_currency_spinner,
                expense_reimbursement_switch,
                expense_client_related_switch
        )

        withEnterExpenseUseCase(EnterExpenseUseCase(
                InMemoryExpenseRepository(),
                DirectTaskExecutor(),
                UUIDSource()
        ))

        val dateFormat = DateFormat.getDateInstance()
        val calendar = Calendar.getInstance()
        val today = dateFormat.format(calendar.time)

        expense_date_input.setText(today)

        val decimalFormat = NumberFormat.getInstance()
        val defaultAmount = decimalFormat.format(0.00)

        expense_amount_input.setText(defaultAmount)

        expense_currency_spinner.adapter = ArrayAdapter.createFromResource(
                this,
                R.array.currency_entries,
                android.R.layout.simple_spinner_item
        )
    }

    fun onDateInputClick(view: View) {
        val today = Calendar.getInstance()

        val dialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    updateDate(year, month, dayOfMonth)
                },
                today.get(YEAR),
                today.get(MONTH),
                today.get(DAY_OF_MONTH)
        )

        dialog.setTitle(R.string.pick_expense_date)
        dialog.show()
    }

    private fun updateDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }

        val dateFormat = DateFormat.getDateInstance()
        val formattedDate = dateFormat.format(calendar.time)

        expense_date_input.setText(formattedDate)
    }

    fun onSaveButtonClick(view: View) {
        val expenseData = ExpenseData(
                comment = expense_comment_input_view.text.toString(),
                date = parseDate(expense_date_input_view.text.toString()),
                cents = amountToCents(expense_amount_input_view.text.toString()),
                currency = expense_currency_spinner_view.selectedItem.toString(),
                needsReimbursement = expense_reimbursement_switch_view.isChecked,
                clientRelated = expense_client_related_switch_view.isChecked
        )
        enterExpenseUseCase.enterExpense(expenseData)

        navigator.showExpenseDetails(this)
    }

    private fun parseDate(string: String): Date {
        val dateFormatter = DateFormat.getDateInstance()
        return dateFormatter.parse(string)
    }

    private fun amountToCents(amount: String) =
            (amount.toDouble() * 100).roundToInt()
}

