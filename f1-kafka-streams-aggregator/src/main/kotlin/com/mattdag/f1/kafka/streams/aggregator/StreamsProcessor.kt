package com.mattdag.f1.kafka.streams.aggregator

import com.mattdag.f1.kafka.streams.aggregator.serdes.Person
import com.mattdag.f1.kafka.streams.aggregator.serdes.PersonSerde
import com.mattdag.f1.kafka.streams.aggregator.serdes.Total
import com.mattdag.f1.kafka.streams.aggregator.serdes.TotalSerde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class StreamsProcessor {

    @Autowired
    fun buildPipeline(streamsBuilder: StreamsBuilder) {
        val messageStream: KStream<String, Person> = streamsBuilder
            .stream("input-topic", Consumed.with(Serdes.String(), PersonSerde()))

        val windowedStream = messageStream
            .peek { key, person -> println("Recieved new message key: $key salary: ${person.salary}") }
            .groupByKey()
            .windowedBy(
                SessionWindows.ofInactivityGapWithNoGrace(Duration.ofSeconds(10))
            )

        val ktable = windowedStream.aggregate(
            { Total() },
            { key, newValue, aggregated ->
                println("[key=$key]: Adding ${newValue.salary}")
                aggregated.total += newValue.salary
                aggregated
            },
            { key, newValue, aggregated ->
                println("[key=$key]: Merging ${newValue.total}")
                aggregated.total += newValue.total
                aggregated
            },
            Materialized.with(Serdes.String(), TotalSerde())
        ).suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))

        return ktable.toStream()
            .map { wk, value -> KeyValue.pair(wk.key(), value) }
            .peek { key, value -> println("Sending [key=$key, total=${value.total}]") }
            .to("output-topic", Produced.with(Serdes.String(), TotalSerde()))
    }
}
