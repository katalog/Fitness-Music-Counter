package com.mkstudio.FitnessMusicCounter.repo

import android.content.Context
import android.content.SharedPreferences
import com.mkstudio.FitnessMusicCounter.util.CommonUtil
import com.mkstudio.FitnessMusicCounter.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalPreferences @Inject constructor(private val con: Context) {
    private val prefs: SharedPreferences =
        con.getSharedPreferences(Constants.APPLICATION_ID, Context.MODE_PRIVATE)

    private fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    private fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getReps(): Int {
        return prefs.getInt(Constants.KEY_REPS, 5)
    }

    fun keepReps(v: Int) {
        prefs.edit().putInt(Constants.KEY_REPS, v).apply()
    }

    fun getIsFirstRun(): Boolean {
        return prefs.getBoolean(Constants.KEY_IS_FIRST_RUN, true)
    }

    fun keepIsFirstRun(v: Boolean) {
        prefs.edit().putBoolean(Constants.KEY_IS_FIRST_RUN, v).apply()
    }

    fun isShowAdmobToday(): Boolean {
        val today = CommonUtil.getTodayFormatted()
        return prefs.getBoolean(today, false)
    }

    fun keepShowAdmobToday() {
        val today = CommonUtil.getTodayFormatted()
        prefs.edit().putBoolean(today, true).apply()
    }
}