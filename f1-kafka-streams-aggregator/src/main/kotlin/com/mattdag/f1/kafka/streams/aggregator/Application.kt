package com.mattdag.f1.kafka.streams.aggregator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams

@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
class F1KafkaStreamsAggregator

fun main(args: Array<String>) {
    runApplication<F1KafkaStreamsAggregator>(*args)
}
