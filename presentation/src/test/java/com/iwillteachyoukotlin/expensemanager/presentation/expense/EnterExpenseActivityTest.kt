package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import com.iwillteachyoukotlin.expensemanager.domain.expense.EnterExpenseUseCase
import com.iwillteachyoukotlin.expensemanager.domain.expense.EnteredExpense
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseData
import com.iwillteachyoukotlin.expensemanager.domain.util.DirectTaskExecutor
import com.iwillteachyoukotlin.expensemanager.presentation.util.MockNavigator
import com.iwillteachyoukotlin.expensemanager.presentation.util.any
import com.iwillteachyoukotlin.expensemanager.presentation.util.makeEditable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.text.DateFormat
import java.text.SimpleDateFormat

class EnterExpenseActivityTest {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    private val defaultDateFormatter = DateFormat.getDateInstance()

    private val navigator = MockNavigator()
    private val enterExpenseUseCase = mock(EnterExpenseUseCase::class.java)

    private val expense_comment_input = mock(EditText::class.java)
    private val expense_date_input = mock(EditText::class.java)
    private val expense_amount_input = mock(EditText::class.java)
    private val expense_currency_spinner = mock(Spinner::class.java)
    private val expense_reimbursement_switch = mock(Switch::class.java)
    private val expense_client_related_switch = mock(Switch::class.java)

    private val activity = EnterExpenseActivity()
            .withNavigator(navigator)
            .withEnterExpenseUseCase(enterExpenseUseCase)
            .withViews(
                    expense_comment_input,
                    expense_date_input,
                    expense_amount_input,
                    expense_currency_spinner,
                    expense_reimbursement_switch,
                    expense_client_related_switch
            )

    @Before
    fun beforeEach() {
        val comment = makeEditable("a comment")
        given(expense_comment_input.text)
                .willReturn(comment)

        val parsedDate = dateFormatter.parse("2017-09-21")
        val formattedDate = defaultDateFormatter.format(parsedDate)
        val date = makeEditable(formattedDate)
        given(expense_date_input.text)
                .willReturn(date)

        val amount = makeEditable("19.99")
        given(expense_amount_input.text)
                .willReturn(amount)

        val currency = makeEditable("EUR")
        given(expense_currency_spinner.selectedItem)
                .willReturn(currency)

        given(expense_reimbursement_switch.isChecked)
                .willReturn(true)

        given(expense_client_related_switch.isChecked)
                .willReturn(false)

        given(enterExpenseUseCase.enterExpense(any()))
                .willReturn(DirectTaskExecutor().execute {
                    EnteredExpense("generated-expense-id")
                })
    }

    @Test
    fun onSaveButtonClick_navigatesToShowExpenseDetails() {
        activity.onSaveButtonClick(View(activity))

        assertTrue(navigator.shownExpenseDetails)
    }

    @Test
    fun onSaveButtonClick_savesTheExpense() {
        activity.onSaveButtonClick(View(activity))

        val expectedSavedExpense = ExpenseData(
                comment = "a comment",
                date = dateFormatter.parse("2017-09-21"),
                cents = 1999,
                currency = "EUR",
                needsReimbursement = true,
                clientRelated = false
        )
        verify(enterExpenseUseCase).enterExpense(expectedSavedExpense)
    }

    @Test
    fun onSaveButtonClick_passesExpenseIdToShowExpenseDetails() {
        activity.onSaveButtonClick(View(activity))

        val expectedExpenseId = "generated-expense-id"
        assertEquals(expectedExpenseId, navigator.shownExpenseDetailsWithExpenseId)
    }
}

