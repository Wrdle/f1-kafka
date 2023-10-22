package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class DynamicRacingLineType(val value: Short) {
    TWO_DIMENSIONAL(0),
    THREE_DIMENSIONAL(1);

    companion object {
        val map = DynamicRacingLineType.entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
