package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.text.InputType.*
import com.iwillteachyoukotlin.expensemanager.HomeActivity
import com.iwillteachyoukotlin.expensemanager.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RunWith(AndroidJUnit4::class)
class EnterExpenseFeatureInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<HomeActivity> =
            ActivityTestRule(HomeActivity::class.java)

    private val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

    private val decimalFormat = NumberFormat.getInstance()

    @Test
    fun jamesCanEnterExpense() {
        // Given I am on the “Home” screen
        onView(withId(R.id.home_screen))
                .check(matches(isDisplayed()))

        // When I tap on “Add Expense” button
        onView(withId(R.id.add_expense_button))
                .perform(click())

        // Then I see the form with the fields:
        onView(withId(R.id.add_expense_screen))
                .check(matches(isDisplayed()))

        // - Date (defaults to today for convenience)
        val dateType = TYPE_CLASS_DATETIME and TYPE_DATETIME_VARIATION_DATE
        onView(withId(R.id.expense_date_input))
                .check(matches(withInputType(dateType)))
                .check(matches(withText(today)))

        // - Amount
        val decimalType = TYPE_CLASS_NUMBER and TYPE_NUMBER_FLAG_DECIMAL
        onView(withId(R.id.expense_amount_input))
                .check(matches(withInputType(
                        decimalType)))
                .check(matches(withText(decimalFormat.format(0.00))))

        // - Currency drop down (single option for now – Euro)
        onView(withId(R.id.expense_currency_spinner))
                .check(matches(withSpinnerText("EUR")))

        // - Needs reimbursement (check box)
        onView(withId(R.id.expense_reimbursement_checkbox))
                .check(matches(isNotChecked()))

        // - Client-related (check box)
        onView(withId(R.id.expense_client_related_checkbox))
                .check(matches(isNotChecked()))

        // - Comment
        onView(withId(R.id.expense_comment_input))
                .check(matches(withText("")))


        // When I enter all the data
        // And I tap on “Save” button
        // Then I see the “Expense Details” screen
        // And I see all the data I’ve entered
    }
}