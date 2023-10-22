package com.mattdag.f1.kafka.common.packets

import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
sealed class Packet {
    abstract var header: PacketHeader

    open fun fill(byteBuffer: ByteBuf): Packet {
        header.fill(byteBuffer)
        return this
    }
}
