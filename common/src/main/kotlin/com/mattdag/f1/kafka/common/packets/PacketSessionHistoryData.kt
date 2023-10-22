package com.mattdag.f1.kafka.common.packets

import com.mattdag.f1.kafka.common.data.LapHistoryData
import com.mattdag.f1.kafka.common.data.TyreStintHistoryData
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

private const val LAP_HISTORY_DATA = 100
private const val TYRE_STINT_HISTORY_DATA = 8

@Serializable
data class PacketSessionHistoryData(
    override var header: PacketHeader = PacketHeader(),
    var carIdx: Short? = null,
    var numTyreStints: Short? = null,
    var bestLapTimeLapNum: Short? = null,
    var bestSector1LapNum: Short? = null,
    var bestSector2LapNum: Short? = null,
    var bestSector3LapNum: Short? = null,
    var lapHistoryData: List<LapHistoryData>? = ArrayList(LAP_HISTORY_DATA),
    var tyreStintsHistoryData: List<TyreStintHistoryData>? = ArrayList(TYRE_STINT_HISTORY_DATA)
) : Packet() {

    override fun fill(byteBuffer: ByteBuf): PacketSessionHistoryData {
        super.fill(byteBuffer)
        carIdx = byteBuffer.readUnsignedByte()
        numTyreStints = byteBuffer.readUnsignedByte()
        bestLapTimeLapNum = byteBuffer.readUnsignedByte()
        bestSector1LapNum = byteBuffer.readUnsignedByte()
        bestSector2LapNum = byteBuffer.readUnsignedByte()
        bestSector3LapNum = byteBuffer.readUnsignedByte()
        lapHistoryData = (0 until LAP_HISTORY_DATA).map { LapHistoryData().apply { fill(byteBuffer) } }
        tyreStintsHistoryData = (0 until TYRE_STINT_HISTORY_DATA)
            .map { TyreStintHistoryData().apply { fill(byteBuffer) } }
        return this
    }
}
