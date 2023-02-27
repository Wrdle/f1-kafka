package com.mattdag.f1.udp.kafka.producer.service

import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel
import io.netty.util.concurrent.Future
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import java.net.InetSocketAddress

@Service
@ConditionalOnProperty("app.udpListener.enabled")
class PacketEventMonitorService(
    inetSocketAddress: InetSocketAddress,
    udpChannelHandler: ChannelHandler
) {

    private val group: EventLoopGroup
    private val bootstrap: Bootstrap

    init {
        group = NioEventLoopGroup()
        bootstrap = Bootstrap()
        bootstrap.group(group)
            .channel(NioDatagramChannel::class.java)
            .option(ChannelOption.SO_BROADCAST, true)
            .handler(udpChannelHandler)
            .localAddress(inetSocketAddress)
    }

    fun bind(): Channel = bootstrap.bind().syncUninterruptibly().channel()

    fun stop(): Future<*> = group.shutdownGracefully()
}
