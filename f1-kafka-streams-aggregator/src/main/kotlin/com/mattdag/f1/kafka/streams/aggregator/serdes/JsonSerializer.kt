package com.mattdag.f1.kafka.streams.aggregator.serdes

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer


class JsonSerializer<T> : Serializer<T> {
    override fun serialize(s: String?, o: T): ByteArray? {
        var retVal: ByteArray? = null
        val objectMapper = ObjectMapper()
        try {
            retVal = objectMapper.writeValueAsBytes(o)
        } catch (e: Exception) {
            println(e.message)
        }
        return retVal
    }

    override fun close() {
        // Empty functional block
    }
}