package com.iwillteachyoukotlin.expensemanager.domain.util

import org.junit.Assert.*
import org.junit.Test

class ResultTest {

    @Test
    fun `when result is created – it is not resolved yet`() {
        val result = Result<String>()
        assertFalse("result is not resolved", result.isResolved())
    }

    @Test
    fun `when result is resolved – it is resolved`() {
        val result = Result<String>()
        result.resolve("hello world")
        assertTrue("result is resolved", result.isResolved())
    }

    @Test
    fun `when result is resolved – onSuccess is called`() {
        val result = Result<String>()

        var onSuccessCalledWith = ""
        result.onSuccess { onSuccessCalledWith = it }

        result.resolve("hello world")

        assertEquals("hello world", onSuccessCalledWith)
    }

    @Test
    fun `when result is resolved before onSuccess is set`() {
        val result = Result<String>()
        result.resolve("some value")

        var onSuccessCalledWith = ""
        result.onSuccess { onSuccessCalledWith = it }

        assertEquals("some value", onSuccessCalledWith)
    }
}