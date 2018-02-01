package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.iwillteachyoukotlin.expensemanager.R
import kotlinx.android.synthetic.main.activity_enter_expense.*
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*
import java.util.Calendar.*

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
        val intent = Intent(this, ShowExpenseDetailsActivity::class.java)
        startActivity(intent)
    }
}
