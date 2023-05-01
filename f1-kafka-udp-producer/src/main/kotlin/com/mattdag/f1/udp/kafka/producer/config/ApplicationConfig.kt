package com.mattdag.f1.udp.kafka.producer.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.valiktor.functions.isNotBlank
import org.valiktor.validate
import java.io.File

const val DEFAULT_PORT = 20777

@ConfigurationProperties(prefix = "app")
@ConstructorBinding
data class ApplicationConfig(
    val producer: ProducerProperties = ProducerProperties(),
    val udpListener: UdpProperties = UdpProperties(),
    val packetReplay: ReplayProperties = ReplayProperties(),
)

class ProducerProperties {
    var enabled: Boolean = true
    val topicConfiguration: TopicConfiguration = TopicConfiguration()
}

@ConstructorBinding
data class TopicConfiguration(
    val carSetupDataTopic: String = "F1--CAR-SETUP-DATA--SRC",
    val carStatusDataTopic: String = "F1--CAR-STATUS-DATA--SRC",
    val carTelemetryDataTopic: String = "F1--CAR-TELEMETRY-DATA--SRC",
    val eventDataTopic: String = "F1--EVENT-DATA--SRC",
    val finalClassificationDataTopic: String = "F1--FINAL-CLASSIFICATION-DATA--SRC",
    val lapDataTopic: String = "F1--LAP-DATA--SRC",
    val lobbyInfoDataTopic: String = "F1--LOBBY-INFO-DATA--SRC",
    val motionDataTopic: String = "F1--MOTION-DATA--SRC",
    val participantsDataTopic: String = "F1--PARTICIPANTS-DATA--SRC",
    val sessionDataTopic: String = "F1--SESSION-DATA--SRC"
) {
    init {
        validate(this) {
            validate(TopicConfiguration::carSetupDataTopic).isNotBlank()
            validate(TopicConfiguration::carStatusDataTopic).isNotBlank()
            validate(TopicConfiguration::carTelemetryDataTopic).isNotBlank()
            validate(TopicConfiguration::eventDataTopic).isNotBlank()
            validate(TopicConfiguration::finalClassificationDataTopic).isNotBlank()
            validate(TopicConfiguration::lapDataTopic).isNotBlank()
            validate(TopicConfiguration::lobbyInfoDataTopic).isNotBlank()
            validate(TopicConfiguration::motionDataTopic).isNotBlank()
            validate(TopicConfiguration::participantsDataTopic).isNotBlank()
            validate(TopicConfiguration::sessionDataTopic).isNotBlank()
        }
    }
}

class UdpProperties {
    var enabled: Boolean = true
    var port: Int = DEFAULT_PORT
}

class ReplayProperties {
    var enabled: Boolean = false
    var file: File? = null
}
