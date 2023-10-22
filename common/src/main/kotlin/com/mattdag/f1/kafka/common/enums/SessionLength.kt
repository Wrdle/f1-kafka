package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class SessionLength(val value: Short) {
    NONE(0),
    VERY_SHORT(2),
    SHORT(3),
    MEDIUM(4),
    MEDIUM_LONG(5),
    LONG(6),
    FULL(7);

    companion object {
        val map = entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
