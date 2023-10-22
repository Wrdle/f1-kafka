package com.mattdag.f1.kafka.common.data

import com.mattdag.f1.kafka.common.enums.Driver
import com.mattdag.f1.kafka.common.enums.Nationality
import com.mattdag.f1.kafka.common.enums.Team
import com.mattdag.f1.kafka.common.enums.YourTelemetry
import com.mattdag.f1.kafka.common.util.readString
import io.netty.buffer.ByteBuf
import kotlinx.serialization.Serializable

private const val NAME_SIZE = 48

@Serializable
data class ParticipantData(
    var aiControlled: Boolean? = null,
    var driverId: Driver? = null,
    var networkId: Short? = null,
    var teamId: Team? = null,
    var myTeam: Boolean? = null,
    var raceNumber: Short? = null,
    var nationality: Nationality? = null,
    var name: String? = null,
    var yourTelemetry: YourTelemetry? = null
) {
    fun fill(byteBuf: ByteBuf) {
        aiControlled = byteBuf.readUnsignedByte().toInt() == 1 // Whether the vehicle is AI (1) or Human (0) controlled
        driverId = Driver.fromValue(byteBuf.readUnsignedByte().toInt())
        networkId = byteBuf.readUnsignedByte()
        teamId = Team.fromValue(byteBuf.readUnsignedByte().toInt())
        myTeam = byteBuf.readUnsignedByte().toInt() == 1 // My team flag – 1 = My Team, 0 = otherwise
        raceNumber = byteBuf.readUnsignedByte()
        nationality = Nationality.fromValue(byteBuf.readUnsignedByte().toInt())
        name = byteBuf.readString(NAME_SIZE)
        yourTelemetry = YourTelemetry.fromValue(byteBuf.readUnsignedByte().toInt())
    }
}