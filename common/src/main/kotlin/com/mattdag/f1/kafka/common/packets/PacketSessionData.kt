package com.mattdag.f1.kafka.common.packets

import com.mattdag.f1.kafka.common.data.MarshalZone
import com.mattdag.f1.kafka.common.data.WeatherForecastSample
import com.mattdag.f1.kafka.common.enums.BrakingAssist
import com.mattdag.f1.kafka.common.enums.DynamicRacingLine
import com.mattdag.f1.kafka.common.enums.DynamicRacingLineType
import com.mattdag.f1.kafka.common.enums.ForecastAccuracy
import com.mattdag.f1.kafka.common.enums.Formula
import com.mattdag.f1.kafka.common.enums.GameMode
import com.mattdag.f1.kafka.common.enums.GearboxAssist
import com.mattdag.f1.kafka.common.enums.NetworkGame
import com.mattdag.f1.kafka.common.enums.Ruleset
import com.mattdag.f1.kafka.common.enums.SafetyCarStatus
import com.mattdag.f1.kafka.common.enums.SessionLength
import com.mattdag.f1.kafka.common.enums.SessionType
import com.mattdag.f1.kafka.common.enums.Track
import com.mattdag.f1.kafka.common.enums.Weather
import com.mattdag.f1.kafka.common.util.toBoolean
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

private const val MARSHAL_ZONES = 21

@Serializable
data class PacketSessionData(
    override var header: PacketHeader = PacketHeader(),
    var weather: Weather? = null,
    var trackTemperature: Short? = null,
    var airTemperature: Short? = null,
    var totalLaps: Short? = null,
    var trackLength: Int? = null,
    var sessionType: SessionType? = null,
    var trackId: Track? = null,
    var formula: Formula? = null,
    var sessionTimeLeft: Int? = null,
    var sessionDuration: Int? = null,
    var pitSpeedLimit: Short? = null,
    var gamePaused: Short? = null,
    var isSpectating: Short? = null,
    var spectatorCarIndex: Short? = null,
    var sliProNativeSupport: Boolean? = null,
    var numMarshalZones: Short? = null,
    var marshalZones: List<MarshalZone>? = ArrayList(MARSHAL_ZONES),
    var safetyCarStatus: SafetyCarStatus? = null,
    var networkGame: NetworkGame? = null,
    var numWeatherForecastSamples: Short? = null,
    var weatherForecastSamples: List<WeatherForecastSample>? = null,
    var forecastAccuracy: ForecastAccuracy? = null,
    var aiDifficulty: Short? = null,
    var seasonLinkIdentifier: Long? = null,
    var weekendLinkIdentifier: Long? = null,
    var sessionLinkIdentifier: Long? = null,
    var pitStopWindowIdealLap: Short? = null,
    var pitStopWindowLatestLap: Short? = null,
    var pitStopRejoinPosition: Short? = null,
    var steeringAssist: Boolean? = null,
    var brakingAssist: BrakingAssist? = null,
    var gearboxAssist: GearboxAssist? = null,
    var pitAssist: Boolean? = null,
    var pitReleaseAssist: Boolean? = null,
    var ersAssist: Boolean? = null,
    var drsAssist: Boolean? = null,
    var dynamicRacingLine: DynamicRacingLine? = null,
    var dynamicRacingLineType: DynamicRacingLineType? = null,
    var gameMode: GameMode? = null,
    var ruleSet: Ruleset? = null,
    var timeOfDay: Long? = null,
    var sessionLength: SessionLength? = null,
) : Packet() {

    override fun fill(byteBuffer: ByteBuf): PacketSessionData {
        super.fill(byteBuffer)
        weather = Weather.fromValue(byteBuffer.readUnsignedByte())
        trackTemperature = byteBuffer.readByte().toShort()
        airTemperature = byteBuffer.readByte().toShort()
        totalLaps = byteBuffer.readUnsignedByte()
        trackLength = byteBuffer.readUnsignedShortLE()
        sessionType = SessionType.fromValue(byteBuffer.readUnsignedByte())
        trackId = Track.fromId(byteBuffer.readByte().toShort())
        formula = Formula.fromValue(byteBuffer.readUnsignedByte())
        sessionTimeLeft = byteBuffer.readUnsignedShortLE()
        sessionDuration = byteBuffer.readUnsignedShortLE()
        pitSpeedLimit = byteBuffer.readUnsignedByte()
        gamePaused = byteBuffer.readUnsignedByte()
        isSpectating = byteBuffer.readUnsignedByte()
        spectatorCarIndex = byteBuffer.readUnsignedByte()
        sliProNativeSupport = byteBuffer.readUnsignedByte().toInt().toBoolean()
        numMarshalZones = byteBuffer.readUnsignedByte()
        marshalZones = (0 until MARSHAL_ZONES).map { MarshalZone().apply { fill(byteBuffer) } }
        safetyCarStatus = SafetyCarStatus.fromValue(byteBuffer.readUnsignedByte())
        networkGame = NetworkGame.fromValue(byteBuffer.readUnsignedByte())
        numWeatherForecastSamples = byteBuffer.readUnsignedByte()
        weatherForecastSamples = (0 until numWeatherForecastSamples!!)
            .map { WeatherForecastSample().apply { fill(byteBuffer) } }
        forecastAccuracy = ForecastAccuracy.fromValue(byteBuffer.readUnsignedByte())
        aiDifficulty = byteBuffer.readUnsignedByte()
        seasonLinkIdentifier = byteBuffer.readUnsignedIntLE()
        weekendLinkIdentifier = byteBuffer.readUnsignedIntLE()
        sessionLinkIdentifier = byteBuffer.readUnsignedIntLE()
        pitStopWindowIdealLap = byteBuffer.readUnsignedByte()
        pitStopWindowLatestLap = byteBuffer.readUnsignedByte()
        pitStopRejoinPosition = byteBuffer.readUnsignedByte()
        steeringAssist = byteBuffer.readUnsignedByte().toBoolean()
        brakingAssist = BrakingAssist.fromValue(byteBuffer.readUnsignedByte())
        gearboxAssist = GearboxAssist.fromValue(byteBuffer.readUnsignedByte())
        pitAssist = byteBuffer.readUnsignedByte().toInt().toBoolean()
        pitReleaseAssist = byteBuffer.readUnsignedByte().toBoolean()
        ersAssist = byteBuffer.readUnsignedByte().toBoolean()
        drsAssist = byteBuffer.readUnsignedByte().toBoolean()
        dynamicRacingLine = DynamicRacingLine.fromValue(byteBuffer.readUnsignedByte())
        dynamicRacingLineType = DynamicRacingLineType.fromValue(byteBuffer.readUnsignedByte())
        gameMode = GameMode.fromValue(byteBuffer.readUnsignedByte())
        ruleSet = Ruleset.fromValue(byteBuffer.readUnsignedByte())
        timeOfDay = byteBuffer.readUnsignedIntLE()
        sessionLength = SessionLength.fromValue(byteBuffer.readUnsignedByte())
        return this
    }
}
