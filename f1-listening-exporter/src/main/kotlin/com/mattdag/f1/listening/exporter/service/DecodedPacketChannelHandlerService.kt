package com.mattdag.f1.listening.exporter.service

import com.mattdag.f1.listening.exporter.config.ApplicationConfig
import com.mattdag.f1.listening.exporter.exporter.Exporter
import com.mattdag.f1.listening.exporter.model.DecodedPacket
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class DecodedPacketChannelHandlerService(
    applicationConfig: ApplicationConfig,
    private val exporters: List<Exporter>
) : SimpleChannelInboundHandler<DecodedPacket>() {

    val maxPacketCount = applicationConfig.numMessages
    var packetCounter = 0L
    private val logger = KotlinLogging.logger {}

    init {
        val simpleNames = exporters.map { it.javaClass.simpleName }
        logger.info { "Exporters found: $simpleNames" }

        if (simpleNames.isEmpty()) {
            logger.warn { "No exporters enabled." }
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext, decodedPacket: DecodedPacket) {
        exporters.forEach { it.export(decodedPacket) }

        trackPacketCount(ctx)
    }

    private fun trackPacketCount(ctx: ChannelHandlerContext) {
        packetCounter++;
        if (packetCounter >= maxPacketCount) {
            closeUdpChannels(ctx)
        }
    }

    private fun closeUdpChannels(ctx: ChannelHandlerContext) {
        ctx.channel().close()
    }
}
