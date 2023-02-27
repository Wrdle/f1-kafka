package com.mattdag.f1.udp.kafka.producer.runners

import com.fasterxml.jackson.databind.ObjectMapper
import com.mattdag.f1.udp.kafka.producer.config.ApplicationConfig
import com.mattdag.f1.udp.kafka.producer.config.ReplayProperties
import com.mattdag.f1.udp.kafka.producer.data.JsonDataWrapper
import com.mattdag.f1.udp.kafka.producer.service.PacketReplayService
import io.ppatierno.formula1.enums.PacketId
import io.ppatierno.formula1.packets.Packet
import io.ppatierno.formula1.packets.PacketCarSetupData
import io.ppatierno.formula1.packets.PacketCarStatusData
import io.ppatierno.formula1.packets.PacketCarTelemetryData
import io.ppatierno.formula1.packets.PacketEventData
import io.ppatierno.formula1.packets.PacketFinalClassificationData
import io.ppatierno.formula1.packets.PacketLapData
import io.ppatierno.formula1.packets.PacketLobbyInfoData
import io.ppatierno.formula1.packets.PacketMotionData
import io.ppatierno.formula1.packets.PacketParticipantsData
import io.ppatierno.formula1.packets.PacketSessionData
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.io.File

@Component
@ConditionalOnProperty("app.packetReplay.enabled")
class PacketReplayCommandLineRunner(
    applicationConfig: ApplicationConfig,
    private val packetReplayService: PacketReplayService
) : CommandLineRunner {

    val replayProperties: ReplayProperties = applicationConfig.packetReplay
    val objectMapper: ObjectMapper = ObjectMapper()

    override fun run(vararg args: String?) {
        println("Starting packet replay")
        if (isValidFile(replayProperties.file)) {
            val packetsWrappers = getFileContents(replayProperties.file!!)
            val packets = decodeJsonPackets(packetsWrappers)
            packetReplayService.replayPackets(packets)
        }
    }

    private fun decodeJsonPackets(jsonDataWrappers: List<JsonDataWrapper>): List<Packet> {
        return jsonDataWrappers.map {
            when (it.packetType) {
                PacketId.CAR_SETUPS -> objectMapper.readValue(it.json, PacketCarSetupData::class.java)
                PacketId.CAR_STATUS -> objectMapper.readValue(it.json, PacketCarStatusData::class.java)
                PacketId.CAR_TELEMETRY -> objectMapper.readValue(it.json, PacketCarTelemetryData::class.java)
                PacketId.EVENT -> objectMapper.readValue(it.json, PacketEventData::class.java)
                PacketId.FINAL_CLASSIFICATION -> objectMapper.readValue(
                    it.json,
                    PacketFinalClassificationData::class.java
                )

                PacketId.LAP_DATA -> objectMapper.readValue(it.json, PacketLapData::class.java)
                PacketId.LOBBY_INFO -> objectMapper.readValue(it.json, PacketLobbyInfoData::class.java)
                PacketId.MOTION -> objectMapper.readValue(it.json, PacketMotionData::class.java)
                PacketId.PARTICIPANTS -> objectMapper.readValue(it.json, PacketParticipantsData::class.java)
                PacketId.SESSION -> objectMapper.readValue(it.json, PacketSessionData::class.java)
                else -> throw IllegalArgumentException("PacketId=${it.packetType} not supported")
            }
        }
    }

    private fun getFileContents(file: File): List<JsonDataWrapper> =
        objectMapper.readValue(file, constructListType()) as List<JsonDataWrapper>

    private fun constructListType() =
        objectMapper.typeFactory.constructParametricType(List::class.java, JsonDataWrapper::class.java)

    private fun isValidFile(file: File?) =
        file != null && file.isFile && file.canRead()
}
