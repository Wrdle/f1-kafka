package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class ActualTyreCompound(val value: Short) {
    C5(16),
    C4(17),
    C3(18),
    C2(19),
    C1(20),
    INTER(7),
    WET(8),
    CLASSIC_DRY(9),
    CLASSIC_WET(10),
    F2_SUPER_SOFT(11),
    F2_SOFT(12),
    F2_MEDIUM(13),
    F2_HARD(14),
    F2_WET(15);

    companion object {
        val map = ActualTyreCompound.entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
