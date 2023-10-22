package com.mattdag.f1.kafka.common.data

import com.mattdag.f1.kafka.common.enums.ActualTyreCompound
import com.mattdag.f1.kafka.common.enums.VisualTyreCompound
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
data class TyreStintHistoryData(
    var endLap: Short? = null,
    var tyreActualCompound: ActualTyreCompound? = null,
    var tyreVisualCompound: VisualTyreCompound? = null
) {

    fun fill(byteBuffer: ByteBuf) {
        endLap = byteBuffer.readUnsignedByte()
        tyreActualCompound = ActualTyreCompound.fromValue(byteBuffer.readUnsignedByte())
        tyreVisualCompound = VisualTyreCompound.fromValue(byteBuffer.readUnsignedByte())
    }
}
