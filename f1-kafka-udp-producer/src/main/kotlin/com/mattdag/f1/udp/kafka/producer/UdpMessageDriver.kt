package com.mattdag.f1.udp.kafka.producer

import com.mattdag.f1.udp.kafka.producer.service.PacketEventMonitorService
import io.netty.channel.Channel
import io.ppatierno.formula1.PacketConfig
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty("app.udpListener.enabled")
class UdpMessageDriver(private val monitor: PacketEventMonitorService) : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Starting producer")

        try {
            val channel: Channel = monitor.bind()
            println("PacketEventMonitor running [season " + PacketConfig.getSeason() + "]")
            channel.closeFuture().sync()
        } finally {
            monitor.stop()
        }
    }
}
