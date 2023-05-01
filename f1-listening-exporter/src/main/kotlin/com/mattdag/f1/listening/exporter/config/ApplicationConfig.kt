package com.mattdag.f1.listening.exporter.config

import mu.KotlinLogging
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.valiktor.functions.isBetween
import org.valiktor.functions.isPositive
import org.valiktor.validate

private const val MAX_PORT_NUMBER = 65535

@ConfigurationProperties(prefix = "app")
@ConstructorBinding
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

@ConstructorBinding
data class UdpProperties(
    var port: Int = 20777
) {
    init {
        validate(this) {
            validate(UdpProperties::port).isBetween(0, MAX_PORT_NUMBER)
        }
    }
}

@ConstructorBinding
data class ExporterProperties(
    var json: JsonExporterProperties = JsonExporterProperties(),
    var udp: UdpRawExporterProperties = UdpRawExporterProperties()
)

@ConstructorBinding
data class JsonExporterProperties(
    var enabled: Boolean = false
)

@ConstructorBinding
data class UdpRawExporterProperties(
    var enabled: Boolean = true
)
