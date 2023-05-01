package com.mattdag.f1.udp.kafka.producer.component

import com.mattdag.f1.udp.kafka.producer.config.ApplicationConfig
import com.mattdag.f1.udp.kafka.producer.config.TopicConfiguration
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
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class TopicMapper(applicationConfig: ApplicationConfig) {

    private val topicMap = constructPacketTopicMap(applicationConfig.producer.topicConfiguration)

    fun getTopic(packet: Packet): String = topicMap[packet::class]!!

    private fun constructPacketTopicMap(topicConfiguration: TopicConfiguration): Map<KClass<out Packet>, String> =
        with(topicConfiguration) {
            mapOf(
                PacketCarSetupData::class to carSetupDataTopic,
                PacketCarStatusData::class to carStatusDataTopic,
                PacketCarTelemetryData::class to carTelemetryDataTopic,
                PacketEventData::class to eventDataTopic,
                PacketFinalClassificationData::class to finalClassificationDataTopic,
                PacketLapData::class to lapDataTopic,
                PacketLobbyInfoData::class to lobbyInfoDataTopic,
                PacketMotionData::class to motionDataTopic,
                PacketParticipantsData::class to participantsDataTopic,
                PacketSessionData::class to sessionDataTopic,
            )
        }
}