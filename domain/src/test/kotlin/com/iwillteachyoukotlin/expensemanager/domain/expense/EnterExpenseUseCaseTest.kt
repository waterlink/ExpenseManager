package com.iwillteachyoukotlin.expensemanager.domain.expense

import com.iwillteachyoukotlin.expensemanager.domain.util.DirectTaskExecutor
import com.iwillteachyoukotlin.expensemanager.domain.util.TestableIdSource
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat

class EnterExpenseUseCaseTest {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")

    @Test
    fun `James can enter expense`() {
        val idSource = TestableIdSource()
        val expenseRepository = InMemoryExpenseRepository()
        val taskExecutor = DirectTaskExecutor()

        // Given expense data
        val expenseData = ExpenseData(
                comment = "Lunch meeting with client",
                date = dateFormatter.parse("2018-01-29"),
                cents = 3500,
                currency = "EUR",
                needsReimbursement = true,
                clientRelated = true
        )

        // When James enters that expense data
        val useCase = EnterExpenseUseCase(
                expenseRepository = expenseRepository,
                taskExecutor = taskExecutor,
                idSource = idSource
        )

        val result = useCase.enterExpense(expenseData)

        result.onSuccess { enteredExpense ->
            // Then expense with that data is created
            val expectedExpense = Expense(
                    id = idSource.lastId,
                    comment = expenseData.comment,
                    date = expenseData.date,
                    cost = Money(3500, Currency.EUR),
                    needsReimbursement = true,
                    clientRelated = true
            )
            val expenses = expenseRepository.findAllExpenses()
            assertEquals(listOf(expectedExpense), expenses)

            // And entered expense id is returned
            assertFalse("id is not empty", enteredExpense.id.isEmpty())
            assertEquals(idSource.lastId, enteredExpense.id)
        }

        assertTrue("result is resolved", result.isResolved())
    }

}