package com.mattdag.f1.udp.kafka.producer.config

import com.mattdag.f1.udp.kafka.producer.component.PacketEventDecoder
import com.mattdag.f1.udp.kafka.producer.component.PacketForwarder
import io.netty.channel.Channel
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetSocketAddress

@Configuration
@ConditionalOnProperty("app.udpListener.enabled")
class PacketEventMonitorConfig(
    private val packetEventDecoder: PacketEventDecoder,
    private val packetForwarder: PacketForwarder
) {

    @Bean
    fun inetSocketAddress(): InetSocketAddress = InetSocketAddress(DEFAULT_PORT)

    @Bean
    fun udpChannelHandler(): ChannelHandler = object : ChannelInitializer<Channel?>() {
        override fun initChannel(ch: Channel?) {
            val pipeline: ChannelPipeline = ch!!.pipeline()
            pipeline.addLast(packetEventDecoder)
            pipeline.addLast(packetForwarder)
        }
    }
}
