package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Formula(val value: Short) {
    F1Modern(0),
    F1Classic(1),
    F2(2),
    F1Generic(3),
    Beta(4),
    Supercars(5),
    Esports(6),
    F22021(7);

    companion object {
        val map = Formula.entries.associateBy { it.value }
        fun fromValue(value: Short) = map[value]
    }
}
