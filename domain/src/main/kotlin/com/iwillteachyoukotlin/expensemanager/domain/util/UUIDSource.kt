package com.iwillteachyoukotlin.expensemanager.domain.util

import java.util.*

class UUIDSource : IdSource {
    override fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}