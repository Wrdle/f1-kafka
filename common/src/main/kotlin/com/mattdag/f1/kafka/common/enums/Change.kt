package com.mattdag.f1.kafka.common.enums

enum class Change(val value: Short) {
    UP(0),
    DOWN(1),
    NO_CHANGE(2);

    companion object {
        val map = Change.entries.associateBy(Change::value)
        fun fromValue(value: Short) = map[value]
    }
}
