package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class FuelMix(val value: Short) {
    LEAN(0),
    STANDARD(1),
    RICH(2),
    MAX(3);

    companion object {
        val map = FuelMix.entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
