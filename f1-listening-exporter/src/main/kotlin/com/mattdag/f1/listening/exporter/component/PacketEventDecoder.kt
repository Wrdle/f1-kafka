package com.mattdag.f1.listening.exporter.component

import com.mattdag.f1.listening.exporter.model.DecodedPacket
import com.mattdag.f1.listening.exporter.util.toByteArraySafe
import io.github.oshai.kotlinlogging.KotlinLogging
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.socket.DatagramPacket
import io.netty.handler.codec.MessageToMessageDecoder
import io.ppatierno.formula1.PacketDecoder
import org.springframework.stereotype.Component

@Component
class PacketEventDecoder : MessageToMessageDecoder<DatagramPacket>() {

    val packetDecoder = PacketDecoder()
    val logger = KotlinLogging.logger {}

    @Throws(Exception::class)
    override fun decode(
        ctx: ChannelHandlerContext,
        datagramPacket: DatagramPacket,
        list: MutableList<Any>
    ) {
        logger.info { "Decoding packet" }

        val byteArray = datagramPacket.content().toByteArraySafe()

        val buffer = datagramPacket.content()
//        val packet = packetDecoder.decode(buffer)
//        list.add(DecodedPacket(packet, byteArray))
        list.add(DecodedPacket(null, byteArray))
    }
}
