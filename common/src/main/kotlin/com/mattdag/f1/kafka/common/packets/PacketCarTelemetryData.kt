package com.mattdag.f1.kafka.common.packets

import com.mattdag.f1.kafka.common.data.CarTelemetryData
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
data class PacketCarTelemetryData(
    override var header: PacketHeader = PacketHeader(),
    var carTelemetryData: List<CarTelemetryData>? = ArrayList(NUM_DRIVERS),
    var mfdPanelIndex: Short? = null,
    var mfdPanelIndexSecondaryPlayer: Short? = null,
    var suggestedGear: Short? = null,
) : Packet() {
    override fun fill(byteBuffer: ByteBuf): PacketCarTelemetryData {
        super.fill(byteBuffer)
        carTelemetryData = (0 until NUM_DRIVERS).map { CarTelemetryData().apply { fill(byteBuffer) } }
        mfdPanelIndex = byteBuffer.readUnsignedByte()
        mfdPanelIndexSecondaryPlayer = byteBuffer.readUnsignedByte()
        suggestedGear = byteBuffer.readByte().toShort()
        return this
    }
}
