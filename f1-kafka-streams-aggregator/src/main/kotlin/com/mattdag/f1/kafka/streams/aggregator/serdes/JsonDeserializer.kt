package com.mattdag.f1.kafka.streams.aggregator.serdes

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer


class JsonDeserializer<T>(private val type: Class<*>) : Deserializer<T> {

    override fun deserialize(s: String?, bytes: ByteArray?): T {
        val mapper = ObjectMapper()
        return mapper.readValue(bytes, type) as T
    }

    override fun close() {
        // Empty functional block
    }
}