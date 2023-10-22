package com.mattdag.f1.kafka.common.enums

enum class TractionControl(val value: Short) {
    OFF(0),
    MEDIUM(1),
    FULL(2);

    companion object {
        val map = TractionControl.entries.associateBy { it.value }

        fun fromValue(value: Short) = map[value]
    }
}