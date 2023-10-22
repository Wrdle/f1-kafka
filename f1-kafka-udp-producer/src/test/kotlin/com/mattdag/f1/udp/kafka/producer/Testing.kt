package com.mattdag.f1.udp.kafka.producer

import com.mattdag.f1.kafka.common.Decoder
import com.mattdag.f1.kafka.common.packets.Packet
import io.netty.buffer.Unpooled
import io.netty.channel.socket.DatagramPacket
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import java.io.File
import java.net.InetSocketAddress

class Testing {

    @Test
    fun test() {
        val decoder = Decoder()

        val packets = File("../test").walk()
            .filter { it.isFile }
            .sortedBy { it.nameWithoutExtension.toLong() }
            .toList()
            .map {
                val byteBuf = Unpooled.wrappedBuffer(it.readBytes())
                val address = InetSocketAddress(0)
                val datagramPacket = DatagramPacket(byteBuf, address)

                val result = decoder.decode(datagramPacket.content())
                result
            }

        val result = Json.encodeToString(packets.first())
        val decoded = Json.decodeFromString<Packet>(result)
        println(decoded)
    }

}