package com.iwillteachyoukotlin.expensemanager.domain

import org.junit.Assert.*
import org.junit.Test

class GreetingTest {

    @Test
    fun `greets the user`() {
        val greeting = Greeting()
        val text = greeting.greet()
        assertEquals("Hello, user!", text)
    }
}