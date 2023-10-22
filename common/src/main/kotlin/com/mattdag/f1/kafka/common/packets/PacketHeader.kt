package com.mattdag.f1.kafka.common.packets

import com.mattdag.f1.kafka.common.enums.PacketId
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
data class PacketHeader(
    var packetFormat: Short? = null,
    var gameMajorVersion: Short? = null,
    var gameMinorVersion: Short? = null,
    var packetVersion: Short? = null,
    var packetId: PacketId? = null,
    var sessionUID: Long? = null,
    var sessionTime: Float? = null,
    var frameIdentifier: Long? = null,
    var playerCarIndex: Short? = null,
    var secondaryPlayerCarIndex: Short? = null // 255 if no second player
) {
    fun fill(byteBuffer: ByteBuf) {
        packetFormat = byteBuffer.readUnsignedShortLE().toShort()
        gameMajorVersion = byteBuffer.readUnsignedByte()
        gameMinorVersion = byteBuffer.readUnsignedByte()
        packetVersion = byteBuffer.readUnsignedByte()
        packetId = PacketId.fromValue(byteBuffer.readUnsignedByte().toInt())
        sessionUID = byteBuffer.readLongLE()
        sessionTime = byteBuffer.readFloatLE()
        frameIdentifier = byteBuffer.readUnsignedIntLE()
        playerCarIndex = byteBuffer.readUnsignedByte()
        secondaryPlayerCarIndex = byteBuffer.readUnsignedByte()
    }
}
