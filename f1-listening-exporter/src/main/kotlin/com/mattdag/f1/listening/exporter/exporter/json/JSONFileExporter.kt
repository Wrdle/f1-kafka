package com.mattdag.f1.listening.exporter.exporter.json

import com.fasterxml.jackson.databind.ObjectMapper
import io.ppatierno.formula1.packets.Packet
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime.now

@Component
class JSONFileExporter {

    private val savedPackets = ArrayList<Packet>()
    private val folderName = "json - " + now().toString()
        // Remove colons which are not valid folder name characters in Windows
        .replace(":", ".")

    private val objectMapper: ObjectMapper = ObjectMapper()

    fun savePacket(packet: Packet) {
        val frameID = packet.header.frameIdentifier
        val packetType = packet.header.packetId.name

        val folderPath = "$folderName/$frameID"
        Files.createDirectories(Paths.get(folderPath))

        prettyWriteAnyToJSONFile("$folderPath/$packetType.json", packet)

        savedPackets.add(packet)
    }

    fun writeAllAppended() {
        if (savedPackets.isNotEmpty()) {
            prettyWriteAnyToJSONFile("$folderName/ALL.json", savedPackets)
        }
    }

    fun writeAllSeperatedByPacketType() {
        if (savedPackets.isNotEmpty()) {
            savedPackets.groupBy { it.header.packetId.name }
                .forEach { (packetType, packetList) ->
                    prettyWriteAnyToJSONFile("$folderName/ALL_$packetType.json", packetList)
                }
        }
    }

    fun writeExtraInfoFile() {
        val firstPacketTime = savedPackets.first().header.sessionTime
        val lastPacketTime = savedPackets.last().header.sessionTime
        val extraInfo = "First packet received time = $firstPacketTime\n" +
                "Last packet received time = $lastPacketTime"
        writeToFile("$folderName/EXTRA_INFO.txt", extraInfo)
    }

    private fun prettyWriteAnyToJSONFile(filePath: String, value: Any) {
        writeToFile(filePath, getPrettyJSON(value))
    }

    private fun writeToFile(filePath: String, value: String) {
        val outputFile = File(filePath)
        outputFile.appendText(value)
    }

    private fun getPrettyJSON(value: Any): String =
        objectMapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(value)
}
