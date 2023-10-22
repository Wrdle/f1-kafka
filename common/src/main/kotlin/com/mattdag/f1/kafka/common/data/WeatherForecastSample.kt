package com.mattdag.f1.kafka.common.data

import com.mattdag.f1.kafka.common.enums.Change
import com.mattdag.f1.kafka.common.enums.SessionType
import com.mattdag.f1.kafka.common.enums.Weather
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastSample(
    var sessionType: SessionType? = null,
    var timeOffset: Short? = null,
    var weather: Weather? = null,
    var trackTemperature: Short? = null,
    var trackTemperatureChange: Change? = null,
    var airTemperature: Short? = null,
    var airTemperatureChange: Change? = null,
    var rainPercentage: Short? = null
) {
    fun fill(byteBuf: ByteBuf): WeatherForecastSample {
        sessionType = SessionType.fromValue(byteBuf.readUnsignedByte())
        timeOffset = byteBuf.readUnsignedByte()
        weather = Weather.fromValue(byteBuf.readUnsignedByte())
        trackTemperature = byteBuf.readByte().toShort()
        trackTemperatureChange = Change.fromValue(byteBuf.readByte().toShort())
        airTemperature = byteBuf.readByte().toShort()
        airTemperatureChange = Change.fromValue(byteBuf.readByte().toShort())
        rainPercentage = byteBuf.readUnsignedByte()
        return this
    }
}
