package com.mattdag.f1.listening.exporter.exporter

import com.mattdag.f1.listening.exporter.model.DecodedPacket

interface Exporter {

    fun export(decodedPacket: DecodedPacket)

}
