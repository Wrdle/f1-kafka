package com.mattdag.f1.listening.exporter.exporter.udp

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime

class IncrementingByteArrayFileExporter {

    private val folderName = "udp - " + LocalDateTime.now().toString()
        // Remove colons which are not valid folder name characters in Windows
        .replace(":", ".")
    var counter = 0
        private set

    init {
        Files.createDirectories(Paths.get(folderName))
    }

    fun saveByteArrayToFile(byteArray: ByteArray) {
        val outputFile = File("$folderName/$counter.udp")
        outputFile.writeBytes(byteArray)
        counter++
    }
}
