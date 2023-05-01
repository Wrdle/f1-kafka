package com.mattdag.f1.udp.kafka.producer

import com.mattdag.f1.udp.kafka.producer.service.PacketReplayService
import io.netty.buffer.Unpooled
import io.netty.channel.socket.DatagramPacket
import io.ppatierno.formula1.PacketDecoder
import io.ppatierno.formula1.packets.Packet
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.io.File
import java.net.InetSocketAddress

@Component
@ConditionalOnProperty("app.packetReplay.enabled")
class ReplayMessageDriver(private val packetReplayService: PacketReplayService) : CommandLineRunner {

    @Value("\${app.packetReplay.folder}")
    private lateinit var udpPacketsFolder: File

    private val logger = KotlinLogging.logger {}
    private val packetDecoder = PacketDecoder()

    override fun run(vararg args: String?) {
        logger.info { "Starting packet replay..." }
        val packets = decodeSavedUdpPackets()
        packetReplayService.replayPackets(packets)
    }

    private fun decodeSavedUdpPackets(): List<Packet> =
        udpPacketsFolder.walk()
            .filter { it.isFile }
            .sortedBy { it.nameWithoutExtension.toLong() }
            .toList()
            .map {
                val byteBuf = Unpooled.wrappedBuffer(it.readBytes())
                val address = InetSocketAddress(0)
                val datagramPacket = DatagramPacket(byteBuf, address)

                packetDecoder.decode(datagramPacket.content())
            }
}

