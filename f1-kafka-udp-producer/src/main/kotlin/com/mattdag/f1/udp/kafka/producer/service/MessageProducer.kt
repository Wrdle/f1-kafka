package com.mattdag.f1.udp.kafka.producer.service

import mu.KotlinLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageProducer {

    private val logger = KotlinLogging.logger {}

    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>? = null

    fun sendMessage(topic: String, frameId: String, msg: String) {
        kafkaTemplate!!.send(ProducerRecord(topic, frameId, msg))
        logger.info { "Sent packet data" }
    }
}

