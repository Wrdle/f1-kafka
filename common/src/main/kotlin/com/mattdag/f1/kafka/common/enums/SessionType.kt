package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class SessionType(val value: Short) {
    UNKNOWN(0),
    P1(1),
    P2(2),
    P3(3),
    SHORT_P(4),
    Q1(5),
    Q2(6),
    Q3(7),
    SHORT_Q(8),
    OSQ(9),
    R(10),
    R2(11),
    R3(12),
    TIME_TRIAL(13);

    companion object {
        val map = entries.associateBy { it.value }
        fun fromValue(value: Short) = SessionType.map[value]
    }
}
