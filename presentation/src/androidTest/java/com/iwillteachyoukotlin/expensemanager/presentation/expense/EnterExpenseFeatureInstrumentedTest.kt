package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.PickerActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.text.InputType.*
import android.widget.DatePicker
import com.iwillteachyoukotlin.expensemanager.HomeActivity
import com.iwillteachyoukotlin.expensemanager.R
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*
import java.util.Calendar.*


@RunWith(AndroidJUnit4::class)
class EnterExpenseFeatureInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<HomeActivity> =
            ActivityTestRule(HomeActivity::class.java)

    private val dateFormat = DateFormat.getDateInstance()
    private val calendar = Calendar.getInstance()
    private val today = dateFormat.format(calendar.time)

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
        val dateType = TYPE_CLASS_DATETIME or TYPE_DATETIME_VARIATION_DATE
        onView(withId(R.id.expense_date_input))
                .check(matches(withInputType(dateType)))
                .check(matches(withText(today)))

        // - Amount
        val decimalType = TYPE_CLASS_NUMBER or TYPE_NUMBER_FLAG_DECIMAL
        onView(withId(R.id.expense_amount_input))
                .check(matches(withInputType(
                        decimalType)))
                .check(matches(withText(decimalFormat.format(0.00))))

        // - Currency drop down (single option for now – Euro)
        onView(withId(R.id.expense_currency_spinner))
                .check(matches(withSpinnerText("EUR")))

        // - Needs reimbursement (check box)
        onView(withId(R.id.expense_reimbursement_switch))
                .check(matches(isNotChecked()))

        // - Client-related (check box)
        onView(withId(R.id.expense_client_related_switch))
                .check(matches(isNotChecked()))

        // - Comment
        onView(withId(R.id.expense_comment_input))
                .check(matches(withText("")))

        // When I enter all the data:
        // - Date = yesterday
        val yesterdayCalendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }
        val yesterday = dateFormat.format(yesterdayCalendar.time)
        // tap on the date input once to trigger date picker dialog
        onView(withId(R.id.expense_date_input)).perform(click())
        // set date using the date picker dialog
        onView(withClassName(equalTo(DatePicker::class.java.name)))
                .perform(PickerActions.setDate(
                        yesterdayCalendar.get(YEAR),
                        // de-normalizing (switching to 1-based index)
                        yesterdayCalendar.get(MONTH) + 1,
                        yesterdayCalendar.get(DAY_OF_MONTH)
                ))
        // confirm selection of that date
        onView(withId(android.R.id.button1)).perform(click())
        // verify the correct date was selected
        onView(withId(R.id.expense_date_input))
                .check(matches(withText(yesterday)))

        // - Amount = 17.30
        val expectedAmount = 17.30
        val formattedAmount = decimalFormat.format(expectedAmount)
        onView(withId(R.id.expense_amount_input))
                .perform(clearText(), typeText(formattedAmount))
                .check(matches(withText(formattedAmount)))

        // - Currency drop down = EUR
        // 1. open the spinner
        onView(withId(R.id.expense_currency_spinner))
                .perform(click())
        // 2. when data is loaded and shown, click on “EUR”
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("EUR")))
                .perform(click())
        // 3. double-check that it is actually selected
        onView(withId(R.id.expense_currency_spinner))
                .check(matches(withSpinnerText("EUR")))

        // - Needs reimbursement = true
        onView(withId(R.id.expense_reimbursement_switch))
                .perform(click())
                .check(matches(isChecked()))

        // - Client-related = true
        onView(withId(R.id.expense_client_related_switch))
                .perform(click())
                .check(matches(isChecked()))

        // - Comment = “taxi to the airport”
        val expectedComment = "taxi to the airport"
        onView(withId(R.id.expense_comment_input))
                .perform(typeText(expectedComment))
                .check(matches(withText(expectedComment)))

        // And I tap on “Save” button
        // Then I see the “Expense Details” screen
        // And I see all the data I’ve entered
    }
}