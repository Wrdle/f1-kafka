package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class NetworkGame(val value: Short) {
    OFFLINE(0),
    ONLINE(1);

    companion object {
        val map = NetworkGame.entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
