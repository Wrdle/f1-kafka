package com.mattdag.f1.udp.kafka.producer.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.mattdag.f1.udp.kafka.producer.component.TopicMapper
import io.ppatierno.formula1.packets.Packet
import mu.KotlinLogging
import org.springframework.stereotype.Service

private const val MILLIS_MULTIPLIER = 1000

@Service
class PacketReplayService(
    private val topicMapper: TopicMapper,
    private val messageProducer: MessageProducer
) {

    private val logger = KotlinLogging.logger { }
    private val objectMapper: ObjectMapper = ObjectMapper()

    fun replayPackets(packets: List<Packet>) {
        for (i in packets.indices) {
            val currentPacket = packets[i]
            val nextPacket = packets.getOrNull(i)

            val topic = topicMapper.getTopic(currentPacket)
            logger.info { "Replaying packet number: $i" }
            messageProducer.sendMessage(
                topic,
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
