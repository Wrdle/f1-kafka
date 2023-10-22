//package com.mattdag.f1.udp.kafka.producer.serdes;
//
//data class CarStatusSerde(
//    var tractionControl: TractionControl,
//    var antiLockBrakes: Short,
//    var fuelMix: FuelMix,
//    var frontBrakeBias: Short,
//    var pitLimiterStatus: Short,
//    var fuelInTank: Float,
//    var fuelCapacity: Float,
//    var fuelRemainingLaps: Float,
//    var maxRPM: Int,
//    var idleRPM: Int,
//    var maxGears: Short,
//    var drsAllowed: DrsAllowed,
//    var drsActivationDistance: Int,
//    var tyresWear: ShortArray = ShortArray(4),
//    var actualTyreCompound: ActualTyreCompound,
//    var visualTyreCompound: VisualTyreCompound,
//    var tyresAgeLaps: Short,
//    var tyresDamage: ShortArray = ShortArray(4),
//    var frontLeftWingDamage: Short,
//    var frontRightWingDamage: Short,
//    var rearWingDamage: Short,
//    var drsFault: Short,
//    var engineDamage: Short,
//    var gearBoxDamage: Short,
//    var vehicleFiaFlags: VehicleFiaFlag,
//    var ersStoreEnergy: Float,
//    var ersDeployMode: ErsDeployMode,
//    var ersHarvestedThisLapMGUK: Float,
//    var ersHarvestedThisLapMGUH: Float,
//    var ersDeployedThisLap: Float
//) : JsonSerdeCompatible
//
//enum class TractionControl(private val value: Int) {
//    OFF(0),
//    MEDIUM(1),
//    HIGH(2);
//}
//
//enum class FuelMix(private val value: Int) {
//    LEAN(0),
//    STANDARD(1),
//    RICH(2),
//    MAX(3);
//}
//
//enum class DrsAllowed(private val value: Int) {
//    UNKNOWN(-1),
//    NOT_ALLOWED(0),
//    ALLOWED(1);
//}
//
//enum class ActualTyreCompound(private val value: Int) {
//    UNKNOWN(0),
//    F1_C5(16),
//    F1_C4(17),
//    F1_C3(18),
//    F1_C2(19),
//    F1_C1(20),
//    F1_INTER(7),
//    F1_WET(8),
//    F1_CLASSIC_DRY(9),
//    F1_CLASSIC_WET(10),
//    F2_SUPER_SOFT(11),
//    F2_SOFT(12),
//    F2_MEDIUM(13),
//    F2_HARD(14),
//    F2_WET(15);
//}
//
//enum class VisualTyreCompound(private val value: Int) {
//    UNKNOWN(0),
//    F1_SOFT(16),
//    F1_MEDIUM(17),
//    F1_HARD(18),
//    F1_INTER(7),
//    F1_WET(8),
//    F1_CLASSIC_DRY(9),
//    F1_CLASSIC_WET(10),
//    F2_SUPER_SOFT(11),
//    F2_SOFT(12),
//    F2_MEDIUM(13),
//    F2_HARD(14),
//    F2_WET(15);
//}
