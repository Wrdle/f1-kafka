package com.mattdag.f1.kafka.common.enums

enum class ErsDeployMode(val value: Short) {
    NONE(0),
    MEDIUM(1),
    HOTLAP(2),
    OVERTAKE(3);

    companion object {
        val map = ErsDeployMode.entries.associateBy { it.value }

        fun fromValue(value: Short) = map[value]
    }
}