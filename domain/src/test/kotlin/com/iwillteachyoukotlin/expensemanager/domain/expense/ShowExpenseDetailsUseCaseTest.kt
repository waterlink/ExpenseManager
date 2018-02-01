package com.iwillteachyoukotlin.expensemanager.domain.expense

import com.iwillteachyoukotlin.expensemanager.domain.util.DirectTaskExecutor
import com.iwillteachyoukotlin.expensemanager.domain.util.TestableIdSource
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ShowExpenseDetailsUseCaseTest {

    private val idSource = TestableIdSource()
    private val expenseRepository = InMemoryExpenseRepository()
    private val taskExecutor = DirectTaskExecutor()

    @Test
    fun `James can view saved expense details`() {
        // Given there is a saved expense
        val expenseId = idSource.generateId()
        val expense = Expense(
                id = expenseId,
                comment = "taxi from airport to the hotel",
                date = LocalDate.parse("2018-01-30", DateTimeFormatter.ISO_DATE),
                cost = Money(1350, Currency.EUR),
                needsReimbursement = true,
                clientRelated = true
        )
        expenseRepository.createExpense(expense)

        // When I request details of that expense by id
        val useCase = ShowExpenseDetailsUseCase(expenseRepository, taskExecutor)
        val result = useCase.showExpenseDetails(expenseId)

        // Then I see the details of that expense
        result.onSuccess { expenseDetails ->
            val expectedDetails = ExpenseDetails(
                    comment = expense.comment,
                    date = expense.date,
                    cost = expense.cost,
                    needsReimbursement = expense.needsReimbursement,
                    clientRelated = expense.clientRelated
            )
            assertEquals(expectedDetails, expenseDetails)
        }

        assertTrue("result is resolved", result.isResolved())
    }

    @Test
    fun `James cannot view non-existing expense details`() {
        // Given there is no expense with such id
        val expenseId = idSource.generateId()

        // When I request expense details for that id
        val useCase = ShowExpenseDetailsUseCase(expenseRepository, taskExecutor)
        val result = useCase.showExpenseDetails(expenseId)

        // Then I get nothing
        result.onSuccess { expenseDetails ->
            assertNull(expenseDetails)
        }

        assertTrue("result is resolved", result.isResolved())
    }
}

