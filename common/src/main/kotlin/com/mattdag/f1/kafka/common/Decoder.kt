package com.mattdag.f1.kafka.common

import com.mattdag.f1.kafka.common.enums.PacketId
import com.mattdag.f1.kafka.common.packets.Packet
import com.mattdag.f1.kafka.common.packets.PacketCarStatusData
import com.mattdag.f1.kafka.common.packets.PacketParticipantsData
import com.mattdag.f1.kafka.common.packets.PacketSessionData
import com.mattdag.f1.kafka.common.packets.PacketSessionHistoryData
import io.netty.buffer.ByteBuf

class Decoder {

    fun decode(byteBuf: ByteBuf): Packet? {
        val packet = when (getPacketId(byteBuf)) {
            PacketId.PARTICIPANTS -> PacketParticipantsData()
            PacketId.CAR_STATUS -> PacketCarStatusData()
            PacketId.SESSION -> PacketSessionData()
            PacketId.SESSION_HISTORY -> PacketSessionHistoryData()
            else -> null
        }?.fill(byteBuf)
        return packet
    }

    private fun getPacketId(buffer: ByteBuf): PacketId {
        return PacketId.fromValue(buffer.getUnsignedByte(5).toInt())
    }
}
