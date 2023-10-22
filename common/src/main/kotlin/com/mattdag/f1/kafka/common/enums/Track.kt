package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Track(val id: Short, val trackName: String) {
    MELBOURNE(0, "Melbourne"),
    PAUL_RICARD(1, "Paul Ricard"),
    SHANGHAI(2, "Shanghai"),
    SAKHIR_BAHRAIN(3, "Sakhir (Bahrain)"),
    CATALUNYA(4, "Catalunya"),
    MONACO(5, "Monaco"),
    MONTREAL(6, "Montreal"),
    SILVERSTONE(7, "Silverstone"),
    HOCKENHEIM(8, "Hockenheim"),
    HUNGARORING(9, "Hungaroring"),
    SPA(10, "Spa"),
    MONZA(11, "Monza"),
    SINGAPORE(12, "Singapore"),
    SUZUKA(13, "Suzuka"),
    ABU_DHABI(14, "Abu Dhabi"),
    TEXAS(15, "Texas"),
    BRAZIL(16, "Brazil"),
    AUSTRIA(17, "Austria"),
    SOCHI(18, "Sochi"),
    MEXICO(19, "Mexico"),
    BAKU_AZERBAIJAN(20, "Baku (Azerbaijan)"),
    SAKHIR_SHORT(21, "Sakhir Short"),
    SILVERSTONE_SHORT(22, "Silverstone Short"),
    TEXAS_SHORT(23, "Texas Short"),
    SUZUKA_SHORT(24, "Suzuka Short"),
    HANOI(25, "Hanoi"),
    ZANDVOORT(26, "Zandvoort"),
    IMOLA(27, "Imola"),
    PORTIMAO(28, "Portimão"),
    JEDDAH(29, "Jeddah"),
    MIAMI(30, "Miami");

    companion object {
        val map = entries.associateBy { it.id }
        fun fromId(id: Short) = map[id]
    }
}
