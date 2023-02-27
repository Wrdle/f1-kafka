package com.mattdag.f1.udp.kafka.producer.data

import com.fasterxml.jackson.databind.ObjectMapper
import io.ppatierno.formula1.enums.PacketId
import io.ppatierno.formula1.packets.Packet

data class JsonDataWrapper(
    var frameID: Long? = null,
    var packetType: PacketId? = null,
    var sessionTime: Float? = null,
    var json: String? = null
)

object JsonDataWrapperUtils {

    private val objectMapper: ObjectMapper = ObjectMapper()

    fun createJsonDataWrapper(packet: Packet): JsonDataWrapper {
        val json = objectMapper.writeValueAsString(packet)
        return createJsonDataWrapper(packet, json)
    }

    fun createPrettyJsonDataWrapper(packet: Packet): JsonDataWrapper {
        val json = objectMapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(packet)
        return createJsonDataWrapper(packet, json)
    }

    private fun createJsonDataWrapper(packet: Packet, json: String): JsonDataWrapper {
        val frameID = packet.header.frameIdentifier
        val packetID = packet.header.packetId
        val sessionTime = packet.header.sessionTime
        return JsonDataWrapper(frameID, packetID, sessionTime, json)
    }
}
