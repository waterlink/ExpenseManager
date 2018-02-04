package com.iwillteachyoukotlin.expensemanager.presentation.expense

import com.iwillteachyoukotlin.expensemanager.R
import com.iwillteachyoukotlin.expensemanager.domain.expense.Currency
import com.iwillteachyoukotlin.expensemanager.domain.expense.ExpenseDetails
import com.iwillteachyoukotlin.expensemanager.domain.expense.Money
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ExpenseDetailsPresenterTest {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    private val defaultDateFormatter = DateFormat.getDateInstance()

    private val numberFormatter = NumberFormat.getCurrencyInstance()

    @Test
    fun presentsDate() {
        val date = dateFormatter.parse("2018-02-03")
        val expenseDetails = createExpenseDetails(date = date)

        val presenter = ExpenseDetailsPresenter(expenseDetails)

        val expectedDate = defaultDateFormatter.format(date)
        assertEquals(expectedDate, presenter.date)
    }

    @Test
    fun presentsCost() {
        val expenseDetails = createExpenseDetails(
                cost = Money(2790, Currency.EUR))

        val presenter = ExpenseDetailsPresenter(expenseDetails)

        numberFormatter.currency = java.util.Currency.getInstance("EUR")
        val expectedCost = numberFormatter.format(27.90)
        assertEquals(expectedCost, presenter.cost)
    }

    @Test
    fun presentsReimbursementLabel_needsReimbursement_whenTrue() {
        val expenseDetails = createExpenseDetails(
                needsReimbursement = true)

        val presenter = ExpenseDetailsPresenter(expenseDetails)

        val expectedLabel = R.string.expense_needs_reimbursement
        assertEquals(expectedLabel, presenter.reimbursementLabel)
    }

    @Test
    fun presentsReimbursementLabel_needsNoReimbursement_whenFalse() {
        val expenseDetails = createExpenseDetails(
                needsReimbursement = false)

        val presenter = ExpenseDetailsPresenter(expenseDetails)

        val expectedLabel = R.string.expense_needs_no_reimbursement
        assertEquals(expectedLabel, presenter.reimbursementLabel)
    }

    @Test
    fun presentsClientRelatedLabel_isClientRelated_whenTrue() {
        val expenseDetails = createExpenseDetails(
                clientRelated = true)

        val presenter = ExpenseDetailsPresenter(expenseDetails)

        val expectedLabel = R.string.expense_client_related
        assertEquals(expectedLabel, presenter.clientRelatedLabel)
    }

    @Test
    fun presentsClientRelatedLabel_isNotClientRelated_whenFalse() {
        val expenseDetails = createExpenseDetails(
                clientRelated = false)

        val presenter = ExpenseDetailsPresenter(expenseDetails)

        val expectedLabel = R.string.expense_not_client_related
        assertEquals(expectedLabel, presenter.clientRelatedLabel)
    }

    @Test
    fun presentsComment() {
        val expenseDetails = createExpenseDetails(
                comment = "hotel lodging")

        val presenter = ExpenseDetailsPresenter(expenseDetails)

        assertEquals("hotel lodging", presenter.comment)
    }

    private fun createExpenseDetails(
            comment: String = "a comment",
            date: Date = dateFormatter.parse("2018-02-03"),
            cost: Money = Money(2790, Currency.EUR),
            needsReimbursement: Boolean = false,
            clientRelated: Boolean = false
    ) =
            ExpenseDetails(
                    comment = comment,
                    date = date,
                    cost = cost,
                    needsReimbursement = needsReimbursement,
                    clientRelated = clientRelated
            )
}