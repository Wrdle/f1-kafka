package com.mattdag.f1.udp.kafka.producer

import com.mattdag.f1.udp.kafka.producer.config.ApplicationConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig::class)
class F1UdpKafkaProducerApplication

fun main(args: Array<String>) {
    runApplication<F1UdpKafkaProducerApplication>(args = args)
}
