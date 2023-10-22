package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Weather(val value: Short) {
    CLEAR(0),
    LIGHT_CLOUD(1),
    OVERCAST(2),
    LIGHT_RAIN(3),
    HEAVY_RAIN(4),
    STORM(5);

    companion object {
        val map = Weather.entries.associateBy { it.value }
        fun fromValue(value: Short) = Weather.map[value]
    }
}
