package com.mattdag.f1.kafka.common.util

fun Short.toBoolean() = this.toInt().toBoolean()

fun Int.toBoolean() = when (this) {
    0 -> false
    1 -> true
    else -> throw IllegalArgumentException("Invalid value for Boolean: $this")
}
