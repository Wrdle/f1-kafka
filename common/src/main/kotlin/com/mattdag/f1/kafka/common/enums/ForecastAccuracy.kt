package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class ForecastAccuracy(val value: Short) {
    PERFECT(0),
    APPROXIMATE(1);

    companion object {
        val map = entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
