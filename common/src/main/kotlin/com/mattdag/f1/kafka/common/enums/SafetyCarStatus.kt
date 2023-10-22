package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class SafetyCarStatus(val value: Short) {
    NO_SAFETY_CAR(0),
    FULL_SAFETY_CAR(1),
    VIRTUAL_SAFETY_CAR(2),
    FORMATION_LAP(3);

    companion object {
        val map = SafetyCarStatus.entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
