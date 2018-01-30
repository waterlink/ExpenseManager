package com.iwillteachyoukotlin.expensemanager.domain.util

import java.util.*

class TestableIdSource : IdSource {
    var lastId: String = ""

    override fun generateId(): String {
        val id = UUID.randomUUID().toString()
        lastId = id
        return id
    }
}