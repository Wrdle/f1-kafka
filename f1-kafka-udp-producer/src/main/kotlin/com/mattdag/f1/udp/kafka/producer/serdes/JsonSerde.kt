//package com.mattdag.f1.udp.kafka.producer.serdes
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import org.apache.kafka.common.errors.SerializationException
//import org.apache.kafka.common.serialization.Deserializer
//import org.apache.kafka.common.serialization.Serde
//import org.apache.kafka.common.serialization.Serializer
//import java.io.IOException
//
//
//class JSONSerde<T : JsonSerdeCompatible?> : Serializer<T>, Deserializer<T>, Serde<T> {
//    override fun configure(configs: Map<String?, *>?, isKey: Boolean) {}
//    override fun deserialize(topic: String?, data: ByteArray?): T? {
//        return if (data == null) {
//            null
//        } else try {
//            OBJECT_MAPPER.readValue(data, JsonSerdeCompatible::class.java) as T
//        } catch (e: IOException) {
//            throw SerializationException(e)
//        }
//    }
//
//    override fun serialize(topic: String?, data: T?): ByteArray? {
//        return if (data == null) {
//            null
//        } else try {
//            OBJECT_MAPPER.writeValueAsBytes(data)
//        } catch (e: Exception) {
//            throw SerializationException("Error serializing JSON message", e)
//        }
//    }
//
//    override fun close() {}
//    override fun serializer(): Serializer<T> {
//        return this
//    }
//
//    override fun deserializer(): Deserializer<T> {
//        return this
//    }
//
//    companion object {
//        private val OBJECT_MAPPER = ObjectMapper()
//    }
//}
