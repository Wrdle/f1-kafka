package com.mattdag.f1.kafka.common.data

import com.mattdag.f1.kafka.common.enums.ActualTyreCompound
import com.mattdag.f1.kafka.common.enums.ErsDeployMode
import com.mattdag.f1.kafka.common.enums.FuelMix
import com.mattdag.f1.kafka.common.enums.TractionControl
import com.mattdag.f1.kafka.common.enums.VehicleFiaFlags
import com.mattdag.f1.kafka.common.enums.VisualTyreCompound
import com.mattdag.f1.kafka.common.util.toBoolean
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

@Serializable
data class CarStatusData(
    var tractionControl: TractionControl? = null,
    var antiLockBrakes: Boolean? = null,
    var fuelMix: FuelMix? = null,
    var frontBrakeBias: Short? = null,
    var pitLimiterStatus: Short? = null,
    var fuelInTank: Float? = null,
    var fuelCapacity: Float? = null,
    var fuelRemainingLaps: Float? = null,
    var maxRPM: Int? = null,
    var idleRPM: Int? = null,
    var maxGears: Short? = null,
    var drsAllowed: Boolean? = null,
    var drsActivationDistance: Int? = null,
    var actualTyreCompound: ActualTyreCompound? = null,
    var visualTyreCompound: VisualTyreCompound? = null,
    var tyresAgeLaps: Short? = null,
    var vehicleFiaFlags: VehicleFiaFlags? = null,
    var ersStoreEnergy: Float? = null,
    var ersDeployMode: ErsDeployMode? = null,
    var ersHarvestedThisLapMGUK: Float? = null,
    var ersHarvestedThisLapMGUH: Float? = null,
    var ersDeployedThisLap: Float? = null,
    var networkPaused: Short? = null
) {
    fun fill(byteBuf: ByteBuf): CarStatusData {
        tractionControl = TractionControl.fromValue(byteBuf.readUnsignedByte())
        antiLockBrakes = byteBuf.readUnsignedByte().toBoolean()
        fuelMix = FuelMix.fromValue(byteBuf.readUnsignedByte())
        frontBrakeBias = byteBuf.readUnsignedByte()
        pitLimiterStatus = byteBuf.readUnsignedByte()
        fuelInTank = byteBuf.readFloatLE()
        fuelCapacity = byteBuf.readFloatLE()
        fuelRemainingLaps = byteBuf.readFloatLE()
        maxRPM = byteBuf.readUnsignedShortLE()
        idleRPM = byteBuf.readUnsignedShortLE()
        maxGears = byteBuf.readUnsignedByte()
        drsAllowed = byteBuf.readUnsignedByte().toBoolean()
        drsActivationDistance = byteBuf.readUnsignedShortLE()
        actualTyreCompound = ActualTyreCompound.fromValue(byteBuf.readUnsignedByte())
        visualTyreCompound = VisualTyreCompound.fromValue(byteBuf.readUnsignedByte())
        tyresAgeLaps = byteBuf.readUnsignedByte()
        vehicleFiaFlags = VehicleFiaFlags.fromValue(byteBuf.readByte().toShort())
        ersStoreEnergy = byteBuf.readFloatLE()
        ersDeployMode = ErsDeployMode.fromValue(byteBuf.readUnsignedByte())
        ersHarvestedThisLapMGUK = byteBuf.readFloatLE()
        ersHarvestedThisLapMGUH = byteBuf.readFloatLE()
        ersDeployedThisLap = byteBuf.readFloatLE()
        networkPaused = byteBuf.readUnsignedByte()
        return this
    }
}
