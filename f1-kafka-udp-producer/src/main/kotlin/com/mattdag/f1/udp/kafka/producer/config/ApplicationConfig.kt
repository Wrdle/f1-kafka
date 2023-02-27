package com.mattdag.f1.udp.kafka.producer.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
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
    var topicName: String = "F1-RAW-DATA"
}

class UdpProperties {
    var enabled: Boolean = true
    var port: Int = DEFAULT_PORT
}

class ReplayProperties {
    var enabled: Boolean = false
    var file: File? = null
}
