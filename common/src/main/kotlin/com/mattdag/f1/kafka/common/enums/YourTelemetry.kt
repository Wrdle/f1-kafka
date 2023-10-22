package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class YourTelemetry(val value: Int) {
    RESTRICTED(0),
    PUBLIC(1),
    UNKNOWN(-1);

    companion object {
        fun fromValue(value: Int): YourTelemetry {
            return YourTelemetry.entries.firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}
