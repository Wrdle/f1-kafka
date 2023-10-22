package com.mattdag.f1.kafka.common.data

import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
data class MarshalZone(
    var zoneStart: Float? = null,
    var zoneFlag: Int? = null
) {

    fun fill(byteBuffer: ByteBuf): MarshalZone {
        zoneStart = byteBuffer.readFloatLE()
        zoneFlag = byteBuffer.readIntLE()
        return this
    }
}
