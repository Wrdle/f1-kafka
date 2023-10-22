package com.mattdag.f1.kafka.common.enums

enum class VisualTyreCompound(val value: Short) {
    SOFT(16),
    MEDIUM(17),
    HARD(18),
    INTER(7),
    WET(8),
    CLASSIC_DRY(9),
    CLASSIC_WET(10),
    F2_SUPER_SOFT(19),
    F2_SOFT(20),
    F2_MEDIUM(21),
    F2_HARD(22),
    F2_WET(15);

    companion object {
        val map = VisualTyreCompound.entries.associateBy { it.value }

        fun fromValue(value: Short) = map[value]
    }
}