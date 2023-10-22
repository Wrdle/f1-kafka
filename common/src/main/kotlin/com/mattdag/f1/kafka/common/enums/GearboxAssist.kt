package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class GearboxAssist(val value: Short) {
    MANUAL(0),
    MANUAL_SUGGESTED_GEAR(1),
    AUTO(2);

    companion object {
        val map = entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
