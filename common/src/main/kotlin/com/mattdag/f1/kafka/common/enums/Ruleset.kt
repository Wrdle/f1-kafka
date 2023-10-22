package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Ruleset(val value: Short) {
    PRACTICE_AND_QUALIFYING(0),
    RACE(1),
    TIME_TRIAL(2),
    TIME_ATTACK(4),
    CHECKPOINT_CHALLENGE(6),
    AUTOCROSS(8),
    DRIFT(9),
    AVERAGE_SPEED_ZONE(10),
    RIVAL_DUEL(11);

    companion object {
        val map = entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
