package com.iwillteachyoukotlin.expensemanager.presentation.util

import android.text.Editable
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

fun makeEditable(value: String): Editable {
    val editable = mock(Editable::class.java)
    given(editable.toString()).willReturn(value)
    return editable
}