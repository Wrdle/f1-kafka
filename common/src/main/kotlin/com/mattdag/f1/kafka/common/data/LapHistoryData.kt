package com.mattdag.f1.kafka.common.data

import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

private const val LAP_VALID_BIT_POSITION = 0
private const val SECTOR_1_VALID_BIT_POSITION = 1
private const val SECTOR_2_VALID_BIT_POSITION = 2
private const val SECTOR_3_VALID_BIT_POSITION = 3

@Serializable
data class LapHistoryData(
    var lapTimeInMs: Long? = null,
    var sector1TimeInMs: Int? = null,
    var sector2TimeInMs: Int? = null,
    var sector3TimeInMs: Int? = null,
    var lapValid: Boolean? = null,
    var sector1Valid: Boolean? = null,
    var sector2Valid: Boolean? = null,
    var sector3Valid: Boolean? = null,
) {
    fun fill(byteBuffer: ByteBuf) {
        lapTimeInMs = byteBuffer.readUnsignedIntLE()
        sector1TimeInMs = byteBuffer.readUnsignedShortLE()
        sector2TimeInMs = byteBuffer.readUnsignedShortLE()
        sector3TimeInMs = byteBuffer.readUnsignedShortLE()

        val lapValidBitFlags = byteBuffer.readUnsignedByte()
        lapValid = retrieveBitFlag(lapValidBitFlags, LAP_VALID_BIT_POSITION)
        sector1Valid = retrieveBitFlag(lapValidBitFlags, SECTOR_1_VALID_BIT_POSITION)
        sector2Valid = retrieveBitFlag(lapValidBitFlags, SECTOR_2_VALID_BIT_POSITION)
        sector3Valid = retrieveBitFlag(lapValidBitFlags, SECTOR_3_VALID_BIT_POSITION)
    }

    /**
     * Adding this explanation because I know I will need it later
     * "1 shl bitPosition" creates a bit mask with a 1 in the corresponding bit position
     * By using "bitwise and" against the bit flags we are left with only the bit in the corresponding position
     * If the value equals 0 then the flag is false else is true
     */
    private fun retrieveBitFlag(bitFlag: Short, bitPosition: Int): Boolean {
        return (bitFlag.toInt() and (1 shl bitPosition)) != 0
    }
}