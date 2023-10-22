package com.mattdag.f1.kafka.common.enums

enum class VehicleFiaFlags(val value: Short) {
    UNKNOWN(-1),
    NONE(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    RED(4);

    companion object {
        val map = entries.associateBy { it.value }

        fun fromValue(value: Short) = map[value] ?: UNKNOWN
    }
}
