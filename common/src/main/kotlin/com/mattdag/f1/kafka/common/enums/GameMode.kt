package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class GameMode(val id: Short) {
    EVENT_MODE(0),
    GRAND_PRIX(3),
    TIME_TRIAL(5),
    SPLIT_SCREEN(6),
    ONLINE_CUSTOM(7),
    ONLINE_LEAGUE(8),
    CAREER_INVITATIONAL(11),
    CHAMPIONSHIP_INVITATIONAL(12),
    CHAMPIONSHIP(13),
    ONLINE_CHAMPIONSHIP(14),
    ONLINE_WEEKLY_EVENT(15),
    CAREER_22(19),
    CAREER_22_ONLINE(20),
    BENCHMARK(127);

    companion object {
        val map = entries.associateBy { it.id }
        fun fromValue(id: Short) = map[id]
    }
}
