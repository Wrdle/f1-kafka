package com.mattdag.f1.kafka.common.packets

import com.mattdag.f1.kafka.common.data.ParticipantData
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

const val NUM_DRIVERS = 22

@Serializable
data class PacketParticipantsData(
    override var header: PacketHeader = PacketHeader(),
    var numActiveCars: Short? = null,
    var participants: List<ParticipantData>? = ArrayList(NUM_DRIVERS)
) : Packet() {

    override fun fill(byteBuffer: ByteBuf): PacketParticipantsData {
        super.fill(byteBuffer)
        numActiveCars = byteBuffer.readUnsignedByte()
        participants = (0 until numActiveCars!!).map { ParticipantData().apply { fill(byteBuffer) } }
        return this
    }
}
