package com.mattdag.f1.kafka.streams.aggregator

import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.KStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafkaStreams

@Configuration
@EnableKafkaStreams
class KafkaStreamsConfig(
    private val jsonTransformerSupplier: JsonTransformerSupplier
) {

    @Bean
    fun kafkaStreamsBuilder(streamsBuilder: StreamsBuilder): KStream<String, String> =
        streamsBuilder.stream<String, String>("F1--CAR-SETUP-DATA--SRC").apply {
            this.transform(jsonTransformerSupplier)
                .to("F1--CAR-SETUP-DATA--TEST")
        }
}
