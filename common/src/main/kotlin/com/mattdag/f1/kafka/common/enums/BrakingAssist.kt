package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class BrakingAssist(val value: Short) {
    OFF(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    companion object {
        val map = BrakingAssist.entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
