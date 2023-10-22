package com.mattdag.f1.kafka.streams.aggregator

import com.fasterxml.jackson.databind.ObjectMapper
import io.ppatierno.formula1.data.CarSetupData
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.Transformer
import org.apache.kafka.streams.kstream.TransformerSupplier
import org.apache.kafka.streams.processor.ProcessorContext
import org.springframework.stereotype.Component

@Component
class JsonTransformerSupplier : TransformerSupplier<String, String, KeyValue<String, CarSetupData>> {

    override fun get(): Transformer<String, String, KeyValue<String, CarSetupData>> {
        return JsonTransformer()
    }
}

@Suppress("SwallowedException", "TooGenericExceptionCaught")
class JsonTransformer : Transformer<String, String, KeyValue<String, CarSetupData>> {

    private lateinit var context: ProcessorContext
    private val mapper = ObjectMapper()

    override fun init(context: ProcessorContext) {
        this.context = context
    }

    override fun transform(key: String?, value: String?): KeyValue<String, CarSetupData>? {
        if (key == null || value == null) {
            return null
        }

        val myJsonClass = try {
            mapper.readValue(value, CarSetupData::class.java)
        } catch (e: Exception) {
            context.headers().add("error", "invalid-json".toByteArray())
            null
        }

        return KeyValue(key, myJsonClass)
    }

    override fun close() {
        // Nothing to do
    }
}
