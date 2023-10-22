package com.mattdag.f1.udp.kafka.producer.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    private val logger = KotlinLogging.logger {}

    fun sendMessage(topic: String, frameId: String, msg: String) {
        kafkaTemplate.send(ProducerRecord(topic, frameId, msg))
        logger.info { "Sent packet data" }
    }
}
