package com.mattdag.f1.kafka.common.data

import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

const val WHEEL_NUM = 4

@Serializable
data class CarTelemetryData(
    var speed: Short? = null,
    var throttle: Float? = null,
    var steer: Float? = null,
    var brake: Float? = null,
    var clutch: Short? = null,
    var gear: Short? = null,
    var engineRPM: Int? = null,
    var drs: Short? = null,
    var revLightsPercent: Short? = null,
    var revLightsBitValue: Int? = null,
    var brakesTemperature: IntArray? = null,
    var tyresSurfaceTemperature: ShortArray? = null,
    var tyresInnerTemperature: ShortArray? = null,
    var engineTemperature: Short? = null,
    var tyresPressure: FloatArray? = null,
    var surfaceType: ShortArray? = null
) {
    fun fill(byteBuf: ByteBuf) {
        speed = byteBuf.readUnsignedShortLE().toShort()
        throttle = byteBuf.readFloatLE()
        steer = byteBuf.readFloatLE()
        brake = byteBuf.readFloatLE()
        clutch = byteBuf.readUnsignedByte()
        gear = byteBuf.readUnsignedByte()
        engineRPM = byteBuf.readUnsignedShortLE()
        drs = byteBuf.readUnsignedByte()
        revLightsPercent = byteBuf.readUnsignedByte()
        revLightsBitValue = byteBuf.readUnsignedShortLE()

        brakesTemperature = IntArray(WHEEL_NUM) { byteBuf.readUnsignedShortLE() }
        tyresSurfaceTemperature = ShortArray(WHEEL_NUM) { byteBuf.readUnsignedByte() }
        tyresInnerTemperature = ShortArray(WHEEL_NUM) { byteBuf.readUnsignedByte() }

        engineTemperature = byteBuf.readShort()

        tyresPressure = FloatArray(WHEEL_NUM) { byteBuf.readFloatLE() }
        surfaceType = ShortArray(WHEEL_NUM) { byteBuf.readUnsignedByte() }
    }
}