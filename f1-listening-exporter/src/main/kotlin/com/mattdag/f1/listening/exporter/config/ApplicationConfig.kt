package com.mattdag.f1.listening.exporter.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.context.properties.ConfigurationProperties
import org.valiktor.functions.isBetween
import org.valiktor.functions.isPositive
import org.valiktor.validate

private const val MAX_PORT_NUMBER = 65535

@ConfigurationProperties(prefix = "app")
data class ApplicationConfig(
    val numMessages: Long = 1000,
    val udpListener: UdpProperties = UdpProperties(),
    val exporters: ExporterProperties = ExporterProperties(),
) {
    init {
        validate(this) {
            validate(ApplicationConfig::numMessages).isPositive()
        }

        val logger = KotlinLogging.logger {}
        logger.info { "Starting with ApplicationConfig: [${this}]" }
    }
}

data class UdpProperties(
    var port: Int = 20777
) {
    init {
        validate(this) {
            validate(UdpProperties::port).isBetween(0, MAX_PORT_NUMBER)
        }
    }
}

data class ExporterProperties(
    var json: JsonExporterProperties = JsonExporterProperties(),
    var udp: UdpRawExporterProperties = UdpRawExporterProperties()
)

data class JsonExporterProperties(
    var enabled: Boolean = false
)

data class UdpRawExporterProperties(
    var enabled: Boolean = true
)
