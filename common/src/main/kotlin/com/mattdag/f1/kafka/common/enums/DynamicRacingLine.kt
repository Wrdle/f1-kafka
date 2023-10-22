package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class DynamicRacingLine(val value: Short) {
    OFF(0),
    CORNERS_ONLY(1),
    FULL(2);

    companion object {
        val map = entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
