package com.mattdag.f1.kafka.streams.aggregator.serdes

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes

class Person {
    val salary: Int = 0
}

class Total {
    var total: Int = 0
}

fun PersonSerde(): Serde<Person> {
    val serializer = JsonSerializer<Person>()
    val deserializer = JsonDeserializer<Person>(Person::class.java)
    return Serdes.serdeFrom(serializer, deserializer)
}

fun TotalSerde(): Serde<Total> {
    val serializer = JsonSerializer<Total>()
    val deserializer = JsonDeserializer<Total>(Total::class.java)
    return Serdes.serdeFrom(serializer, deserializer)
}