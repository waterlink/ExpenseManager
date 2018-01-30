package com.iwillteachyoukotlin.expensemanager.domain.util

interface IdSource {
    fun generateId(): String
}