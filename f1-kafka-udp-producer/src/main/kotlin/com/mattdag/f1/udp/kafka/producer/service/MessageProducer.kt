package com.mattdag.f1.udp.kafka.producer.service

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageProducer {

    @Value(value = "\${kafka.topicName}")
    private val topicName: String? = null

    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>? = null

    fun sendMessage(frameId: String, msg: String) {
        kafkaTemplate!!.send(ProducerRecord(topicName!!, frameId, msg))
        println("Sent packet data")
    }
}
