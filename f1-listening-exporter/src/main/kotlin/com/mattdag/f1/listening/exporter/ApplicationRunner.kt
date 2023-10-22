package com.mattdag.f1.listening.exporter

import com.mattdag.f1.listening.exporter.service.PacketEventMonitorService
import io.github.oshai.kotlinlogging.KotlinLogging
import io.netty.channel.Channel
import io.ppatierno.formula1.PacketConfig
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ApplicationRunner(private val monitor: PacketEventMonitorService) : CommandLineRunner {

    private val logger = KotlinLogging.logger { }

    override fun run(vararg args: String?) {
        try {
            logStarting()
            val channel: Channel = monitor.bind()
            channel.closeFuture().sync()
        } finally {
            logStopping()
            monitor.stop()
        }
    }

    fun logStarting() {
        logger.info { "Starting udp listening exporter" }
        logger.info { "PacketEventMonitor running [season ${PacketConfig.getSeason()}]" }
    }

    fun logStopping() {
        logger.info { "Stopping udp listening exporter" }
    }
}
