package com.mattdag.f1.udp.kafka.producer.component

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.socket.DatagramPacket
import io.netty.handler.codec.MessageToMessageDecoder
import io.ppatierno.formula1.PacketDecoder
import org.springframework.stereotype.Component

@Component
class PacketEventDecoder : MessageToMessageDecoder<DatagramPacket>() {
    private var packetDecoder = PacketDecoder()

    @Throws(Exception::class)
    override fun decode(
        channelHandlerContext: ChannelHandlerContext,
        datagramPacket: DatagramPacket,
        list: MutableList<Any>
    ) {
        val buffer = datagramPacket.content()
        val packet = packetDecoder.decode(buffer)
        list.add(packet)
    }
}
