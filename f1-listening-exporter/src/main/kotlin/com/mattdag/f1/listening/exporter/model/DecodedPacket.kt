package com.mattdag.f1.listening.exporter.model

import io.ppatierno.formula1.packets.Packet

data class DecodedPacket(val packet: Packet, val rawBytes: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DecodedPacket

        if (packet != other.packet) return false
        if (!rawBytes.contentEquals(other.rawBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = packet.hashCode()
        result = 31 * result + rawBytes.contentHashCode()
        return result
    }
}
