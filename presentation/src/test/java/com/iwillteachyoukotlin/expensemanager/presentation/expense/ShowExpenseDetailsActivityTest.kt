package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.content.Intent
import android.widget.TextView
import com.iwillteachyoukotlin.expensemanager.R
import com.iwillteachyoukotlin.expensemanager.domain.expense.Currency
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseDetails
import com.iwillteachyoukotlin.expensemanager.domain.expense.Money
import com.iwillteachyoukotlin.expensemanager.domain.expense.ShowExpenseDetailsUseCase
import com.iwillteachyoukotlin.expensemanager.domain.util.DirectTaskExecutor
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat

class ShowExpenseDetailsActivityTest {

    class TestableShowExpenseDetailsActivity : ShowExpenseDetailsActivity() {

        var startedIntent: Intent? = null

        override fun getIntent(): Intent {
            return startedIntent!!
        }

    }

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    private val defaultDateFormatter = DateFormat.getDateInstance()

    private val decimalFormat = NumberFormat.getInstance()

    private val expense_details_date = mock(TextView::class.java)
    private val expense_details_cost = mock(TextView::class.java)
    private val expense_details_needs_reimbursement = mock(TextView::class.java)
    private val expense_details_client_related = mock(TextView::class.java)
    private val expense_details_comment = mock(TextView::class.java)

    private val showExpenseDetailsUseCase = mock(ShowExpenseDetailsUseCase::class.java)

    private val activity = TestableShowExpenseDetailsActivity()
            .withShowExpenseDetailsUseCase(showExpenseDetailsUseCase)
            .withViews(
                    expense_details_date,
                    expense_details_cost,
                    expense_details_needs_reimbursement,
                    expense_details_client_related,
                    expense_details_comment
            ) as TestableShowExpenseDetailsActivity

    private val intent = mock(Intent::class.java)

    @Before
    fun beforeEach() {
        activity.startedIntent = intent
    }

    @Test
    fun onStart_obtainsExpenseDetails_andShowsThemInTheView() {

        givenExpenseDetails(
                expenseId = "expected-expense-id",
                comment = "a comment",
                date = "2018-02-04",
                cost = Money(1775, Currency.EUR),
                needsReimbursement = true,
                clientRelated = false
        )

        activity.fetchAndShowExpenseDetails()

        val parsedDate = dateFormatter.parse("2018-02-04")
        val formattedDate = defaultDateFormatter.format(parsedDate)
        verify(expense_details_date).text = formattedDate

        val amount = decimalFormat.format(17.75)
        verify(expense_details_cost).text = "$amount EUR"

        verify(expense_details_needs_reimbursement)
                .setText(R.string.expense_needs_reimbursement)

        verify(expense_details_client_related)
                .setText(R.string.expense_not_client_related)

        verify(expense_details_comment).text = "a comment"

    }

    @Test
    fun onStart_whenNoReimbursementNeeded() {
        givenExpenseDetails(needsReimbursement = false)

        activity.fetchAndShowExpenseDetails()

        verify(expense_details_needs_reimbursement)
                .setText(R.string.expense_needs_no_reimbursement)
    }

    @Test
    fun onStart_whenClientRelated() {
        givenExpenseDetails(clientRelated = true)

        activity.fetchAndShowExpenseDetails()

        verify(expense_details_client_related)
                .setText(R.string.expense_client_related)
    }

    private fun givenExpenseDetails(
            expenseId: String = "expected-expense-id",
            comment: String = "a comment",
            date: String = "2018-02-04",
            cost: Money = Money(1775, Currency.EUR),
            needsReimbursement: Boolean = false,
            clientRelated: Boolean = false) {

        given(intent.getStringExtra("expenseId"))
                .willReturn(expenseId)

        given(showExpenseDetailsUseCase
                .showExpenseDetails(expenseId))
                .willReturn(DirectTaskExecutor().execute {
                    ExpenseDetails(
                            comment = comment,
                            date = dateFormatter.parse(date),
                            cost = cost,
                            needsReimbursement = needsReimbursement,
                            clientRelated = clientRelated
                    )
                })
    }
}