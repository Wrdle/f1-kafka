package com.mattdag.f1.kafka.common.packets

import com.mattdag.f1.kafka.common.data.CarStatusData
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
data class PacketCarStatusData(
    override var header: PacketHeader = PacketHeader(),
    var carStatusData: List<CarStatusData>? = ArrayList(NUM_DRIVERS)
) : Packet() {

    override fun fill(byteBuffer: ByteBuf): PacketCarStatusData {
        super.fill(byteBuffer)
        carStatusData = (0 until NUM_DRIVERS).map { CarStatusData().apply { fill(byteBuffer) } }
        return this
    }
}
