package com.mattdag.f1.listening.exporter.exporter.udp

import com.mattdag.f1.listening.exporter.exporter.Exporter
import com.mattdag.f1.listening.exporter.model.DecodedPacket
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(
    value = ["app.exporters.udp.enabled"],
    havingValue = "true",
    matchIfMissing = true
)
class UdpRawExporterService : Exporter {

    private val exporter = IncrementingByteArrayFileExporter()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Starting ${this.javaClass.simpleName}" }
    }

    override fun export(decodedPacket: DecodedPacket) {
        logger.info { "[UDP] Saving packet number: ${exporter.counter}" }
        exporter.saveByteArrayToFile(decodedPacket.rawBytes)
    }
}
