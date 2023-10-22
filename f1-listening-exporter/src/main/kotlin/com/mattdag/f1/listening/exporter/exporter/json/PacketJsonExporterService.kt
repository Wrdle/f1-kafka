package com.mattdag.f1.listening.exporter.exporter.json

import com.mattdag.f1.listening.exporter.exporter.Exporter
import com.mattdag.f1.listening.exporter.model.DecodedPacket
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(
    value = ["app.exporters.json.enabled"],
    havingValue = "true",
    matchIfMissing = true
)
class PacketJsonExporterService : Exporter {

    private val jsonFileExporter = JSONFileExporter()
    private val logger = KotlinLogging.logger { }

    init {
        logger.info { "Starting ${this.javaClass.simpleName}" }
    }

    override fun export(decodedPacket: DecodedPacket) {
        logger.info { "[JSON] Saving packet" }
        jsonFileExporter.savePacket(decodedPacket.packet!!)
    }

    fun finishExport() {
        jsonFileExporter.writeAllAppended()
        jsonFileExporter.writeAllSeperatedByPacketType()
        jsonFileExporter.writeExtraInfoFile();
    }
}
