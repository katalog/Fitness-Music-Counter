package com.mkstudio.FitnessMusicCounter.ui.views.count

import java.util.*
import kotlin.concurrent.timer

class CountTimer() {
    private lateinit var myTimer: Timer
    private var startTime:Long? = null
    var setCountTimerListener: (v:String) -> Unit = {}

    fun startTimer() {
        myTimer = timer(period = 1000) {
            startTime = startTime ?: System.currentTimeMillis()
            val newTimeMils = System.currentTimeMillis()
            val nBaseSec = (newTimeMils - startTime!!) / 1000
            val nSec = nBaseSec % 60
            val nMin = nBaseSec / 60 % 60
            val nHour = nBaseSec / 60 / 60

            setCountTimerListener(String.format("%02d:%02d:%02d", nHour, nMin, nSec))
        }
    }

    fun stopTimer() {
        myTimer?.cancel()
    }
}