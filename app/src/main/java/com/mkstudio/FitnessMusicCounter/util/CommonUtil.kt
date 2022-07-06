package com.mkstudio.FitnessMusicCounter.util

import java.text.SimpleDateFormat

object CommonUtil {
    fun getTodayFormatted(): String {
        val mTimeMils = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy/M/dd")
        var strDatetime = sdf.format(mTimeMils)
        //strDatetime = "key_" + strDatetime
        return strDatetime
    }

    fun getTodayWithTimeFormatted(): String {
        val mTimeMils = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyyy/M/dd hh:mm")
        var strDatetime = sdf.format(mTimeMils)
        //strDatetime = "key_" + strDatetime
        return strDatetime
    }
}
