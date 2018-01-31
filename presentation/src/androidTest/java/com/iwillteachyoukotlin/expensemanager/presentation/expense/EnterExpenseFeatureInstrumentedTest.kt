package com.iwillteachyoukotlin.expensemanager.presentation.expense

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.iwillteachyoukotlin.expensemanager.R
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import com.iwillteachyoukotlin.expensemanager.HomeActivity
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class EnterExpenseFeatureInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<HomeActivity> =
            ActivityTestRule(HomeActivity::class.java)

    @Test
    fun jamesCanEnterExpense() {
        // Given I am on the “Home” screen
        onView(withId(R.id.home_screen))
                .check(matches(isDisplayed()))

        // When I tap on “Add Expense” button
        // Then I see the form with the fields:
        //
        // - Date (defaults to today for convenience)
        // - Amount
        // - Currency drop down (single option for now – Euro)
        // - Needs reimbursement (check box)
        // - Client-related (check box)
        // - Comment
        //
        // When I enter all the data
        // And I tap on “Save” button
        // Then I see the “Expense Details” screen
        // And I see all the data I’ve entered
    }
}