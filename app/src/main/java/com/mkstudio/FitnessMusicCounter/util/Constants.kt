package com.mkstudio.FitnessMusicCounter.util

object Constants {
    val APPLICATION_ID = "com.mkstudio.FitnessMusicCounter"

    val radioStationMap = mapOf(
        "NRJ_Berlin"    to "http://nrj.de/berlin",
        "NRJ_Hit2000"   to "http://nrj.de/hits2000",
        "NRJ_Dance"     to "http://nrj.de/dance",
        "NRJ_HitRemix"  to "http://nrj.de/hitsremix",
        "NRJ_PartyHits" to "http://nrj.de/partyhits",
        "NRJ_Fitness"   to "http://nrj.de/fitness",
    )

    val KEY_REPS = "key_reps"
    val KEY_IS_FIRST_RUN = "key_isfirstrun"

    val STR_NO_RADIO = "NO RADIO"
    val STR_SELECT_RADIO_STATION = "Select Radio Station"
    val STR_INTPUT_REPS_COUNT = "Please input reps count"
    val STR_DEFUALT_WORKOUT_TIME="00:00:00"
}
