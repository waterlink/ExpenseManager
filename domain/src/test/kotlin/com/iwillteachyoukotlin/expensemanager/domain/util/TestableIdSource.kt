package com.iwillteachyoukotlin.expensemanager.domain.util

import java.util.*

class TestableIdSource {
    var lastId: String = ""

    fun generateId(): String {
        val id = UUID.randomUUID().toString()
        lastId = id
        return id
    }
}