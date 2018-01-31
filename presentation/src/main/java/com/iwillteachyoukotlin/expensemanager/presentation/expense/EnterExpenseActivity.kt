package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.iwillteachyoukotlin.expensemanager.R
import kotlinx.android.synthetic.main.activity_enter_expense.*
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

class EnterExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_expense)

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
}
