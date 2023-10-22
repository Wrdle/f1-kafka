package com.mattdag.f1.kafka.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Team(val id: Int) {
    MERCEDES(0),
    FERRARI(1),
    RED_BULL_RACING(2),
    WILLIAMS(3),
    ASTON_MARTIN(4),
    ALPINE(5),
    ALPHA_TAURI(6),
    HAAS(7),
    MCLAREN(8),
    ALFA_ROMEO(9),
    MERCEDES_2020(85),
    FERRARI_2020(86),
    RED_BULL_2020(87),
    WILLIAMS_2020(88),
    RACING_POINT_2020(89),
    RENAULT_2020(90),
    ALPHA_TAURI_2020(91),
    HAAS_2020(92),
    MCLAREN_2020(93),
    ALFA_ROMEO_2020(94),
    ASTON_MARTIN_DB11_V12(95),
    ASTON_MARTIN_VANTAGE_F1_EDITION(96),
    ASTON_MARTIN_VANTAGE_SAFETY_CAR(97),
    FERRARI_F8_TRIBUTO(98),
    FERRARI_ROMA(99),
    MCLAREN_720S(100),
    MCLAREN_ARTURA(101),
    MERCEDES_AMG_GT_BLACK_SERIES_SAFETY_CAR(102),
    MERCEDES_AMG_GTR_PRO(103),
    F1_CUSTOM_TEAM(104),
    PREMA_21(106),
    UNI_VIRTUOSI_21(107),
    CARLIN_21(108),
    HITECH_21(109),
    ART_GP_21(110),
    MP_MOTORSPORT_21(111),
    CHAROUZ_21(112),
    DAMS_21(113),
    CAMPOS_21(114),
    BWT_21(115),
    TRIDENT_21(116),
    PREMA_22(118),
    VIRTUOSI_22(119),
    CARLIN_22(120),
    HITECH_22(121),
    ART_GP_22(122),
    MP_MOTORSPORT_22(123),
    CHAROUZ_22(124),
    DAMS_22(125),
    CAMPOS_22(126),
    VAN_AMERSFOORT_RACING_22(127),
    TRIDENT_22(128),
    UNKNOWN(-1);

    companion object {
        fun fromValue(value: Int): Team {
            return Team.entries.firstOrNull { it.id == value } ?: UNKNOWN
        }
    }
}