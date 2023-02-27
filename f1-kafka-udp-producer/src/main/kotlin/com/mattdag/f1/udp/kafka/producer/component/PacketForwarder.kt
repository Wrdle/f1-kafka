package com.mattdag.f1.udp.kafka.producer.component

import com.fasterxml.jackson.databind.ObjectMapper
import com.mattdag.f1.udp.kafka.producer.data.JsonDataWrapperUtils
import com.mattdag.f1.udp.kafka.producer.service.MessageProducer
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.ppatierno.formula1.packets.Packet
import org.springframework.stereotype.Component

@Component
class PacketForwarder(private val messageProducer: MessageProducer) : SimpleChannelInboundHandler<Packet>() {

    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun channelRead0(ctx: ChannelHandlerContext, packet: Packet) {
        val wrappedSerializedPacket = JsonDataWrapperUtils.createPrettyJsonDataWrapper(packet)
        messageProducer.sendMessage(
            wrappedSerializedPacket.frameID.toString(),
            objectMapper.writeValueAsString(wrappedSerializedPacket)
        )
    }
}
