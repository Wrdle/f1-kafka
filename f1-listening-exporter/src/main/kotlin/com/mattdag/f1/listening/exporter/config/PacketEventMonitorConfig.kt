package com.mattdag.f1.listening.exporter.config

import com.mattdag.f1.listening.exporter.component.PacketEventDecoder
import com.mattdag.f1.listening.exporter.service.DecodedPacketChannelHandlerService
import io.netty.channel.Channel
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetSocketAddress

@Configuration
class PacketEventMonitorConfig(
    applicationConfig: ApplicationConfig,
    private val packetEventDecoder: PacketEventDecoder,
    private val decodedPacketChannelHandlerService: DecodedPacketChannelHandlerService
) {

    private val udpListenPort = applicationConfig.udpListener.port

    @Bean
    fun inetSocketAddress(): InetSocketAddress = InetSocketAddress(udpListenPort)

    @Bean
    fun udpChannelHandler(): ChannelHandler = object : ChannelInitializer<Channel?>() {
        override fun initChannel(ch: Channel?) {
            val pipeline: ChannelPipeline = ch!!.pipeline()
            pipeline.addLast(packetEventDecoder)
            pipeline.addLast(decodedPacketChannelHandlerService)
        }
    }
}
