package com.mattdag.f1.udp.kafka.producer.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.ppatierno.formula1.packets.Packet
import org.springframework.stereotype.Service

private const val MILLIS_MULTIPLIER = 1000

@Service
class PacketReplayService(private val messageProducer: MessageProducer) {

    val objectMapper: ObjectMapper = ObjectMapper()

    fun replayPackets(packets: List<Packet>) {
        for (i in packets.indices) {
            val currentPacket = packets[i]
            val nextPacket = packets.getOrNull(i)

            messageProducer.sendMessage(
                currentPacket.header.frameIdentifier.toString(),
                objectMapper.writeValueAsString(currentPacket)
            )

            if (nextPacket != null)
                sleepUntilNextPacketTime(currentPacket, nextPacket)
        }
    }

    fun sleepUntilNextPacketTime(currentPacket: Packet, nextPacket: Packet) {
        val currentSessionTime = currentPacket.header.sessionTime
        val nextSessionTime = nextPacket.header.sessionTime

        val sleepTime = ((nextSessionTime - currentSessionTime) * MILLIS_MULTIPLIER).toLong()
        Thread.sleep(sleepTime)
    }
}
