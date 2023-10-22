package com.mattdag.f1.kafka.common.enums

enum class ZoneFlag(val value: Int) {
    UNKNOWN(-1),
    NONE(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    RED(4);

    companion object {
        fun fromValue(value: Int): ZoneFlag {
            return entries.firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}
